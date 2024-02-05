package com.daw2.springprimero.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GeneroNotFoundException extends GeneroException {
    public GeneroNotFoundException(String mensaje) {
        super(mensaje);
    }
}

