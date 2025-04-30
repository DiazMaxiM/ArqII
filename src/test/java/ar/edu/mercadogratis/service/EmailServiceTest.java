package ar.edu.mercadogratis.service;

import ar.edu.mercadogratis.infrastructure.adapters.out.config.EmailProvider;
import ar.edu.mercadogratis.application.service.EmailService;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class EmailServiceTest {

    @TestConfiguration
    static class TestContextConfiguration {
        @Bean
        public EmailService emailService(EmailProvider emailProvider) {
            return new EmailService(emailProvider);
        }
    }

    @Autowired
    private EmailService emailService;

    @MockBean
    private EmailProvider emailProvider;

    @Test
    public void testSend() throws EmailException {
        // Arrange
        String receiver = "receiver@mail.com";
        String subject = "any subject";
        String message = "any message";

        Email email = mock(Email.class);
        when(emailProvider.buildEmail()).thenReturn(email);

        // Act
        emailService.send(receiver, subject, message);

        // Assert
        verify(email).addTo(eq(receiver));
        verify(email).setMsg(eq(message));
        verify(email).setSubject(eq(subject));
        verify(email).send();
    }
}
