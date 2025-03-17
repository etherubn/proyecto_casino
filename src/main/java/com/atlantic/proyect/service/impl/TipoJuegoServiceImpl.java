package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.TipoJuegoDtoRequest;
import com.atlantic.proyect.entity.TipoJuego;
import com.atlantic.proyect.repository.TipoJuegoRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.ITipoJuegoService;
import com.atlantic.proyect.utils.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class TipoJuegoServiceImpl extends CRUDImpl<TipoJuego, TipoJuegoDtoRequest,Long> implements ITipoJuegoService {
    private final TipoJuegoRepo tipoJuegoRepo;

    public TipoJuegoServiceImpl(MapperUtil mapperUtil, TipoJuegoRepo tipoJuegoRepo) {
        super(mapperUtil);
        this.tipoJuegoRepo = tipoJuegoRepo;
    }

    @Override
    protected GenericRepo<TipoJuego, Long> genericRepo() {
        return tipoJuegoRepo;
    }

    @Override
    protected Class<TipoJuego> getEntityClass() {
        return TipoJuego.class;
    }

    @Override
    protected Class<TipoJuegoDtoRequest> getDTOClass() {
        return TipoJuegoDtoRequest.class;
    }

    @Override
    protected void setId(TipoJuego entity, Long aLong) {
        entity.setIdTipoJuego(aLong);
    }
}
