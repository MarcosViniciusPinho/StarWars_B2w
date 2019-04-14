package com.b2w.desafio.infrastructure.handler.exception;

import lombok.Getter;

@Getter
public class UniqueException extends RuntimeException {

    private final String mensagemClient;

    public UniqueException(String mensagemClient, String mensagemException) {
        super(mensagemException);
        this.mensagemClient = mensagemClient;
    }

}
