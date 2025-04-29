package ar.edu.mercadogratis.infrastructure.adapters.in.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;
import ar.edu.mercadogratis.application.exception.ApiException;
import ar.edu.mercadogratis.application.exception.ValidationException;
import ar.edu.mercadogratis.application.exception.NotFoundException;
import ar.edu.mercadogratis.application.exception.ApiError;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
        return handleErrorResponse(buildApiError(e, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiError> handleValidationException(ValidationException e) {
        return handleErrorResponse(buildApiError(e, HttpStatus.BAD_REQUEST));
    }

    private ResponseEntity<ApiError> handleErrorResponse(ApiError apiError) {
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    private ApiError buildApiError(ApiException e, HttpStatus status) {
        return ApiError.builder()
                .cause(e.getStatus().toString())
                .message(e.getDescription())
                .status(status)
                .build();
    }

    private ApiError buildApiError(ValidationException e, HttpStatus status) {
        return ApiError.builder()
                .message(e.getMessage())
                .status(status)
                .build();
    }
}
