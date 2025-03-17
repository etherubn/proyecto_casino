package com.atlantic.proyect.exception;

public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String entity) {
        super(String.format("%s not found",entity));
    }
}
