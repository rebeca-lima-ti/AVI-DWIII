package com.autobots.services;

import org.springframework.stereotype.Service;

@Service
public class VerificadorStringNulo {

    public boolean verificar(String dado) {
        return dado == null || dado.isBlank();
    }
}
