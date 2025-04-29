package ar.edu.mercadogratis.application.exceptions;

public class NotFoundException extends ApiException {
    public NotFoundException(String code, String description) {
        super(code, description);
    }
}
