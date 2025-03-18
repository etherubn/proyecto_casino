package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.create.RegistroJuegoDtoRequest;
import com.atlantic.proyect.entity.RegistroJuego;


public interface IRegistroJuegoService extends ICRUDService<RegistroJuegoDtoRequest,Long> {
    RegistroJuegoDtoRequest jugar(RegistroJuegoDtoRequest registroJuegoDtoRequest);
}
