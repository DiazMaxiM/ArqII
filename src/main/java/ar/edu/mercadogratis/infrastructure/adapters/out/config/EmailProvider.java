package ar.edu.mercadogratis.infrastructure.adapters.out.config;

import org.apache.commons.mail.Email;

public interface EmailProvider {
    Email buildEmail();
}
