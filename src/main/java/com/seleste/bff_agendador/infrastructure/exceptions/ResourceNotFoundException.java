package com.seleste.bff_agendador.infrastructure.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String messsage) {
        super(messsage);
    }

    public ResourceNotFoundException(String message, Throwable throwable) {
        super(message);
    }
}
