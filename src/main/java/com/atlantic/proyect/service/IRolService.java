package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.RolDtoRequest;

import java.util.Optional;


public interface IRolService extends ICRUDService<RolDtoRequest,Long> {
    RolDtoRequest findRolByTipoRol(String tipoRol);
}
