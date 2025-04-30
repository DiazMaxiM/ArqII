package ar.edu.mercadogratis.application.service;

import ar.edu.mercadogratis.application.port.out.MoneyAccountPort;
import ar.edu.mercadogratis.application.port.out.UserPersistencePort;
import ar.edu.mercadogratis.domain.model.AddFundsRequest;
import ar.edu.mercadogratis.domain.model.MoneyAccount;
import ar.edu.mercadogratis.domain.model.User;
import ar.edu.mercadogratis.usecase.AddFundsMoneyUseCase;
import ar.edu.mercadogratis.usecase.GetFundsMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.ValidationException;
import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class MoneyAccountService implements AddFundsMoneyUseCase, GetFundsMoneyUseCase {


    private final MoneyAccountPort accountPort;
    private final UserPersistencePort userPort;


    public BigDecimal getFunds(Long userId) {
        User user = userPort.findById(userId)
                .orElseThrow(() -> new ValidationException("Usuario no encontrado: " + userId));

        return accountPort.getByUser(user)    // devuelve Optional<MoneyAccount>
                .map(MoneyAccount::getBalance)         // Optional<BigDecimal>
                .orElseThrow(() ->
                        new ValidationException("Cuenta no encontrada para: " + user.getEmail())
                );
    }


    public MoneyAccount registerAccount(User user) {
        MoneyAccount account = MoneyAccount.builder()
                .balance(BigDecimal.ZERO)
                .user(user)
                .build();

        return accountPort.save(account);
    }

    public BigDecimal debitAmount(User user, BigDecimal amount) {
        MoneyAccount moneyAccount = accountPort.getByUser(user)
                .orElseThrow(() -> new ValidationException("Account not found for user"));

        if(moneyAccount.getBalance().compareTo(amount) < 0) {
            throw new ValidationException("No funds available");
        }

        moneyAccount.subtractFromBalance(amount);
        accountPort.save(moneyAccount);

        return moneyAccount.getBalance();
    }

    public BigDecimal creditAmount(User user, BigDecimal amount) {
        MoneyAccount moneyAccount = accountPort.getByUser(user)
                .orElseThrow(() -> new ValidationException("Account not found for user"));

        moneyAccount.addToBalance(amount);
        accountPort.save(moneyAccount);

        return moneyAccount.getBalance();
    }

    @Override
    public BigDecimal addFunds(AddFundsRequest request) {
        User user = userPort.findById(request.getUserId()).orElse(null);
        return creditAmount(user, request.getAmount());
    }


}
