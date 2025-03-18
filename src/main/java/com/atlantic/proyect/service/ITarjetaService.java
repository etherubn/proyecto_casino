package com.atlantic.proyect.service;

import com.atlantic.proyect.dto.request.create.TarjetaDtoRequest;
import com.atlantic.proyect.dto.request.update.TarjetaRecargaDtoRequest;


public interface ITarjetaService extends ICRUDService<TarjetaDtoRequest,Long> {
    TarjetaDtoRequest recargarTarjeta(String codigo,TarjetaRecargaDtoRequest tarjetaRecargaDtoRequest);
}
