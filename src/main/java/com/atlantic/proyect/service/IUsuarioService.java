package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.UsuarioDtoRequest;
import com.atlantic.proyect.dto.request.UsuarioUpdateDtoRequest;

import java.util.Map;


public interface IUsuarioService extends ICRUDService<UsuarioDtoRequest,Long> {
    UsuarioDtoRequest update(UsuarioUpdateDtoRequest usuarioUpdateDtoRequest, Long aLong);
    Map<String, String> verifyCreateUsernameAndPerson(UsuarioDtoRequest usuarioDtoRequest);
}
