package ar.edu.mercadogratis.infrastructure.adapters.in.web.controllers;

import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.application.service.UserService;
import ar.edu.mercadogratis.usecase.AddUserUseCase;
import ar.edu.mercadogratis.usecase.ChangePassUserUseCase;
import ar.edu.mercadogratis.usecase.ForgetUserUseCase;
import ar.edu.mercadogratis.usecase.LoginUserUseCase;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final AddUserUseCase addUserUseCase;
    private final LoginUserUseCase loginUserUseCase;
    private final ForgetUserUseCase forgetUserUseCase;
    private final ChangePassUserUseCase changePassUserUseCase;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> addUser(@RequestBody User user) {

        return ResponseEntity.ok(addUserUseCase.addUser(user).toString());
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity login(@RequestBody User user) {
        if (loginUserUseCase.login(user)){
            return ResponseEntity.ok().body(user.getId());
        }
        return ResponseEntity.badRequest().body("Invalid user or password");

    }

    @RequestMapping(value = "/user/forgetPassword", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity<String> forgetPassword(@RequestBody User user) {
        forgetUserUseCase.forgetPassword(user.getEmail());
        return ResponseEntity.ok("success");
    }

    @RequestMapping(value = "/user/changePassword", method = RequestMethod.POST, headers = "Accept=application/json")
    public ResponseEntity changePassword(@RequestBody String stringJson) {
        Long idUser;
        try {
            JSONObject userWithNewPassword = new JSONObject(stringJson);
            idUser = changePassUserUseCase.changePassword(userWithNewPassword);
        } catch (RuntimeException err) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
        }
        return ResponseEntity.ok().body(idUser);
    }
}
