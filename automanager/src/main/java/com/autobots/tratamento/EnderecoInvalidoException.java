package com.autobots.tratamento;

public class EnderecoInvalidoException extends RuntimeException {
    public EnderecoInvalidoException(String message) {
        super(message);
    }

    public EnderecoInvalidoException(String message, Throwable cause) {
        super(message, cause);
    }
}
