package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.create.JuegoDtoRequest;
import com.atlantic.proyect.dto.request.update.JuegoUpdateDtoRequest;


public interface IJuegoService extends ICRUDService<JuegoDtoRequest,Long> {
    JuegoDtoRequest update(JuegoUpdateDtoRequest juegoUpdateDtoRequest,Long aLong);
}
