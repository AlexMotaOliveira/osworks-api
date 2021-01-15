package com.algaworks.osworks.domain.exception;

public class EntityNaoEncontradaException extends NegocioException{

    private static final long serialVersionUID = 1L;

    public EntityNaoEncontradaException(String message){
        super(message);
    }
}
