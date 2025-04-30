package ar.edu.mercadogratis.service;

import ar.edu.mercadogratis.application.service.UserService;
import ar.edu.mercadogratis.application.port.out.UserPersistencePort;
import ar.edu.mercadogratis.application.service.IEmailService;
import ar.edu.mercadogratis.application.service.PasswordGeneratorService;
import ar.edu.mercadogratis.application.service.MoneyAccountService;
import ar.edu.mercadogratis.domain.model.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public UserService userService(
                UserPersistencePort userPort,
                IEmailService emailService,
                PasswordGeneratorService passwordGeneratorService,
                MoneyAccountService moneyAccountService) {
            return new UserService(userPort, emailService, passwordGeneratorService, moneyAccountService);
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserPersistencePort userPort;

    @MockBean
    private IEmailService emailService;

    @MockBean
    private PasswordGeneratorService passwordGeneratorService;

    @MockBean
    private MoneyAccountService moneyAccountService;

    @Test
    void testAddUser_NewUser() {
        User user = User.builder()
                .name("Test")
                .lastName("User")
                .email("test@mail.com")
                .cuit("203040")
                .build();
        // simulate no existing user
        when(userPort.findByEmail("test@mail.com")).thenReturn(Optional.empty());
        when(passwordGeneratorService.generateRandom()).thenReturn("genPwd");
        User saved = User.builder()
                .id(123L)
                .name("Test")
                .lastName("User")
                .email("test@mail.com")
                .password("genPwd")
                .cuit("203040")
                .build();
        when(userPort.save(user)).thenReturn(saved);

        Long id = userService.addUser(user);

        assertThat(id).isEqualTo(123L);
        verify(passwordGeneratorService).generateRandom();
        verify(emailService).send(eq("test@mail.com"), contains("Bienvenido"), contains("genPwd"));
        verify(userPort).save(user);
        verify(moneyAccountService).registerAccount(saved);
    }

    @Test
    void testAddUser_ExistingUser() {
        User existing = User.builder().id(999L).email("test@mail.com").build();
        when(userPort.findByEmail("test@mail.com")).thenReturn(Optional.of(existing));

        Long id = userService.addUser(User.builder().email("test@mail.com").build());

        assertThat(id).isEqualTo(999L);
        verify(userPort, never()).save(any());
        verify(emailService, never()).send(anyString(), anyString(), anyString());
        verify(moneyAccountService, never()).registerAccount(any());
    }

    @Test
    void testGetUserById() {
        User user = User.builder().id(123L).build();
        when(userPort.findById(123L)).thenReturn(Optional.of(user));

        User result = userService.getUser(123L);

        assertThat(result).isEqualTo(user);
    }

    @Test
    void testGetUserForMail() {
        User user = User.builder().id(456L).email("a@b").build();
        when(userPort.findByEmail("a@b")).thenReturn(Optional.of(user));

        User result = userService.getUserForMail("a@b");

        assertThat(result).isEqualTo(user);
    }
}
