package ar.edu.mercadogratis.application.exception;

import org.springframework.http.HttpStatus;

public class ApiError {
    private final HttpStatus status;
    private final String message;
    private final String cause;

    private ApiError(ApiErrorBuilder builder) {
        this.status = builder.status;
        this.message = builder.message;
        this.cause = builder.cause;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getCause() {
        return cause;
    }

    public static ApiErrorBuilder builder() {
        return new ApiErrorBuilder();
    }

    public static class ApiErrorBuilder {
        private HttpStatus status;
        private String message;
        private String cause;

        public ApiErrorBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiErrorBuilder cause(String cause) {
            this.cause = cause;
            return this;
        }

        public ApiError build() {
            return new ApiError(this);
        }
    }
}
