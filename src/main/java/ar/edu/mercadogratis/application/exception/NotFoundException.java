package ar.edu.mercadogratis.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends ApiException {
    public NotFoundException(String message) {
        super(ApiError.builder()
            .status(HttpStatus.NOT_FOUND)
            .message(message)
            .cause("Not Found")
            .build());
    }
}
