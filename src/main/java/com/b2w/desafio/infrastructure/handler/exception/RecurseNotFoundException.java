package com.b2w.desafio.infrastructure.handler.exception;

import lombok.Getter;

@Getter
public class RecurseNotFoundException extends RuntimeException {

    private final String mensagemClient;

    public RecurseNotFoundException(String mensagemClient, String mensagemException) {
        super(mensagemException);
        this.mensagemClient = mensagemClient;
    }

}
