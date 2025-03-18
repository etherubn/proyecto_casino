package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.create.JugadorDtoRequest;
import com.atlantic.proyect.dto.request.update.JugadorUpdateDtoRequest;


public interface IJugadorService extends ICRUDService<JugadorDtoRequest,Long> {
    JugadorDtoRequest crearJugador(JugadorDtoRequest jugadorDtoRequest);
    JugadorDtoRequest actualizarJugador(JugadorUpdateDtoRequest jugadorUpdateDtoRequest,Long id);
}
