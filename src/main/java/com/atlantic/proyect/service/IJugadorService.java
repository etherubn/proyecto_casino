package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.JugadorDtoRequest;


public interface IJugadorService extends ICRUDService<JugadorDtoRequest,Long> {
    JugadorDtoRequest crearJugador(JugadorDtoRequest jugadorDtoRequest);
}
