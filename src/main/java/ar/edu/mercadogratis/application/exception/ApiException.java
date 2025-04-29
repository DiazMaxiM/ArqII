package ar.edu.mercadogratis.application.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {

    private final ApiError error;

    public ApiException(ApiError error) {
        super(error.getMessage());
        this.error = error;
    }

    public int getCode() {
        return error.getStatus().value();
    }

    public String getDescription() {
        return error.getMessage();
    }

    public HttpStatus getStatus() {
        return error.getStatus();
    }
}
