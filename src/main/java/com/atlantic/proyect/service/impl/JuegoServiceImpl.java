package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.JuegoDtoRequest;
import com.atlantic.proyect.entity.Juego;
import com.atlantic.proyect.repository.JuegoRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IJuegoService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class JuegoServiceImpl extends CRUDImpl<Juego, JuegoDtoRequest,Long> implements IJuegoService {
    private final JuegoRepo juegoRepo;

    public JuegoServiceImpl(MapperUtil mapperUtil, JuegoRepo juegoRepo) {
        super(mapperUtil);
        this.juegoRepo = juegoRepo;
    }

    @Override
    protected GenericRepo<Juego, Long> genericRepo() {
        return juegoRepo;
    }

    @Override
    protected Class<Juego> getEntityClass() {
        return Juego.class;
    }

    @Override
    protected Class<JuegoDtoRequest> getDTOClass() {
        return JuegoDtoRequest.class;
    }

    @Override
    protected void setId(Juego entity, Long aLong) {
        entity.setIdJuego(aLong);
    }
}
