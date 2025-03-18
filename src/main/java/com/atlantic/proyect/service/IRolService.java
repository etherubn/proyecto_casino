package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.create.RolDtoRequest;


public interface IRolService extends ICRUDService<RolDtoRequest,Long> {
    RolDtoRequest findRolByTipoRol(String tipoRol);
}
