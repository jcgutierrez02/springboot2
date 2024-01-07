package com.daw2.springprimero.exceptions;

public abstract class EjemploException extends RuntimeException {
    public EjemploException(String mensaje) {
        super(mensaje);
    }
}
