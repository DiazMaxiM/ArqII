package ar.edu.mercadogratis.infrastructure.adapters.out.config;

import lombok.SneakyThrows;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.MultiPartEmail;

public class GmailEmailProvider implements EmailProvider {

    @SneakyThrows
    @Override
    public Email buildEmail() {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(587);
        email.setStartTLSEnabled(true);
        email.setAuthentication("mercadogratisunq@gmail.com", "abcd efgh ijkl mnop");
        email.setFrom("mercadogratisunq@gmail.com");

        return email;
    }
}
