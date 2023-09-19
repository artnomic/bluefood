package br.com.artnomic.bluefood.application.service.exception;

public class ApplicationServiceException extends RuntimeException {
    public ApplicationServiceException(String message) {
        super(message);
    }

    public ApplicationServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApplicationServiceException(Throwable cause) {
        super(cause);
    }
}
