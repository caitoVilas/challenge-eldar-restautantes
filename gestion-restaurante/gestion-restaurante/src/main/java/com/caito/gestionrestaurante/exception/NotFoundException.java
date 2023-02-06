package com.caito.gestionrestaurante.exception;


public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg){
        super(msg);
    }
}
