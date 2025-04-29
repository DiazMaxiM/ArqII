package ar.edu.mercadogratis.application.service;

import ar.edu.mercadogratis.application.port.out.UserPersistencePort;
import ar.edu.mercadogratis.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service("userService")
public class UserService {

    private final UserPersistencePort UserAdapter;
    private final IEmailService emailService;
    private final PasswordGeneratorService passwordGeneratorService;
    private final MoneyAccountService moneyAccountService;

    @Transactional
    public User getUser(String id) {
        return UserAdapter.findById(id).orElse(null);
    }

    @Transactional
    public User getUserForMail(String mail) {
        return UserAdapter.findByEmail(mail).orElse(null);
    }

    @Transactional
    public String addUser(User user) {
        String generatedPwd = passwordGeneratorService.generateRandom();
        sendRegistrationEmail(user, generatedPwd);
        user.setPassword(generatedPwd);

        User savedUser = UserAdapter.save(user);
        moneyAccountService.registerAccount(savedUser);

        return savedUser.getId();
    }

    private void sendRegistrationEmail(User user, String generatedPwd) {
        emailService.send(user.getEmail(), "Bienvenido a MercadoGratis", "Tu password es: " + generatedPwd);
    }

    @Transactional
    public void updateUser(User user) {
        UserAdapter.save(user);
    }

    @Transactional
    public void forgetPassword(String mail) {
        User user = this.getUserForMail(mail);
        if (user != null) {
            emailService.send(user.getEmail(), "Bienvenido a MercadoGratis", "Tu password es: " + user.getPassword());
        }
    }

    @Transactional
    public String changePassword(JSONObject userWithNewPassword) throws RuntimeException, JSONException {
        String idUser = null;

        User user = this.getUserForMail(userWithNewPassword.getString("email"));

        if (user != null && user.getPassword().equals(userWithNewPassword.getString("password"))) {
            idUser = user.getId();

            user.setPassword(userWithNewPassword.getString("new_password"));
            UserAdapter.save(user);

        } else {
            throw new RuntimeException("Error User o Password Ingresada");
        }
        return idUser;
    }
}
