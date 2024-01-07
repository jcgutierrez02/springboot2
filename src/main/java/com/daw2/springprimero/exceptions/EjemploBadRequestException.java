package com.daw2.springprimero.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Nos permite devolver un estado cuando salta la excepción
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EjemploBadRequestException extends EjemploException {
    public EjemploBadRequestException(String mensaje) {
        super(mensaje);
    }
}
