package com.example.TP_4.Exepciones;

public class UsuarioNoEncontradoException extends RuntimeException{
    public UsuarioNoEncontradoException(String message) {
        super(message);
    }
}
