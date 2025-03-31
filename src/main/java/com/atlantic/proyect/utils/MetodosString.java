package com.atlantic.proyect.utils;

import org.springframework.stereotype.Component;

@Component
public class MetodosString {

    public String formatearNombre(String nombre){
        return nombre.trim().replaceAll(" ", "_").toUpperCase();
    }
}
