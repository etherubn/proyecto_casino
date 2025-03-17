package com.atlantic.proyect.config;

import com.atlantic.proyect.dto.request.PersonaDtoRequest;
import com.atlantic.proyect.dto.request.UsuarioDtoRequest;
import com.atlantic.proyect.entity.Persona;
import com.atlantic.proyect.entity.Usuario;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean("defaultMapper")
    public ModelMapper getMapper() {
        return new ModelMapper();
    }
}
