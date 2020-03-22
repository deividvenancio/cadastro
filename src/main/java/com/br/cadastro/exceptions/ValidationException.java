package com.br.cadastro.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String message, String ... value) {
        super(java.text.MessageFormat.format(message, value));
    }

    public ValidationException(String message) {
        super(message);
    }
}
