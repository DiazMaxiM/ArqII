package ar.edu.mercadogratis.service;

import ar.edu.mercadogratis.application.service.MoneyAccountService;

import ar.edu.mercadogratis.application.port.out.MoneyAccountPort;
import ar.edu.mercadogratis.application.port.out.UserPersistencePort;
import ar.edu.mercadogratis.infrastructure.adapters.in.web.mapper.MoneyAccountMapper;
import ar.edu.mercadogratis.domain.model.MoneyAccount;
import ar.edu.mercadogratis.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class MoneyAccountServiceTest {


    @Autowired
    private MoneyAccountService moneyAccountService;

    @MockBean
    private MoneyAccountPort accountPort;



    public void testRegisterAccount() {
        // Arrange
        User user = new User(1L, "Name", "Last", "email@mail.com", "pass", "cuit");
        MoneyAccount domainAccount = MoneyAccount.builder()
                .user(user)
                .balance(BigDecimal.ZERO)
                .build();
        when(accountPort.save(domainAccount)).thenReturn(domainAccount);

        // Act
        MoneyAccount result = moneyAccountService.registerAccount(user);

        // Assert
        assertThat(result).isEqualTo(domainAccount);
        verify(accountPort).save(domainAccount);
    }


    public void testCreditAmount() {
        // Arrange
        User user = new User(2L, "Name2", "Last2", "email2@mail.com", "pass2", "cuit2");
        MoneyAccount account = spy(MoneyAccount.builder()
                .user(user)
                .balance(BigDecimal.ONE)
                .build());
        when(accountPort.getByUser(user)).thenReturn(Optional.of(account));
        when(accountPort.save(account)).thenReturn(account);

        // Act
        moneyAccountService.creditAmount(user, new BigDecimal("100"));

        // Assert
        verify(account).addToBalance(new BigDecimal("100"));
        verify(accountPort).save(account);
    }

    public void testDebitAmount() {
        // Arrange
        User user = new User(3L, "Name3", "Last3", "email3@mail.com", "pass3", "cuit3");
        MoneyAccount account = spy(MoneyAccount.builder()
                .user(user)
                .balance(new BigDecimal("100"))
                .build());
        when(accountPort.getByUser(user)).thenReturn(Optional.of(account));
        when(accountPort.save(account)).thenReturn(account);

        // Act
        moneyAccountService.debitAmount(user, new BigDecimal("50"));

        // Assert
        verify(account).subtractFromBalance(new BigDecimal("50"));
        verify(accountPort).save(account);
    }
}
