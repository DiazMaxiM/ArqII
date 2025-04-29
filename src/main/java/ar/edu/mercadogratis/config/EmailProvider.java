package ar.edu.mercadogratis.config;

import org.apache.commons.mail.Email;

public interface EmailProvider {
    Email buildEmail();
}
