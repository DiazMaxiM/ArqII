package ar.edu.mercadogratis.application.service;

public interface IEmailService {
    void send(String receiver, String subject, String message);
}
