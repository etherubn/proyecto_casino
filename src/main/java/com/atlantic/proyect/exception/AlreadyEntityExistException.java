package com.atlantic.proyect.exception;

import java.util.Map;

public class AlreadyEntityExistException extends RuntimeException {
    Map<String,String> map;
    public AlreadyEntityExistException(String entity, Map<String, String> map) {
        super(entity);
        this.map = map;
    }


}
