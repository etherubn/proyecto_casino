package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.JugadorDtoRequest;
import com.atlantic.proyect.entity.Jugador;
import com.atlantic.proyect.repository.JugadorRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IJugadorService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class JugadorServiceImpl extends CRUDImpl<Jugador, JugadorDtoRequest,Long> implements IJugadorService {
    private final JugadorRepo jugadorRepo;

    public JugadorServiceImpl(MapperUtil mapperUtil, JugadorRepo jugadorRepo) {
        super(mapperUtil);
        this.jugadorRepo = jugadorRepo;
    }

    @Override
    protected GenericRepo<Jugador, Long> genericRepo() {
        return jugadorRepo;
    }

    @Override
    protected Class<Jugador> getEntityClass() {
        return Jugador.class;
    }

    @Override
    protected Class<JugadorDtoRequest> getDTOClass() {
        return JugadorDtoRequest.class;
    }

    @Override
    protected void setId(Jugador entity, Long aLong) {
        entity.setIdJugador(aLong);
    }
}
