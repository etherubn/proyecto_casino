package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.TipoJuegoDtoRequest;
import com.atlantic.proyect.entity.TipoJuego;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.repository.TipoJuegoRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.ITipoJuegoService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    @Transactional
    public TipoJuegoDtoRequest save(TipoJuegoDtoRequest tipoJuegoDtoRequest) {
        if (tipoJuegoRepo.existsByNombreIgnoreCase(tipoJuegoDtoRequest.getNombre())) {
            Map<String,String> errors = new HashMap<>();
            errors.put("nombre", tipoJuegoDtoRequest.getNombre());
            throw new AlreadyEntityExistException("tipo juego", errors);
        }
        return super.save(tipoJuegoDtoRequest);
    }

    @Override
    @Transactional
    public TipoJuegoDtoRequest update(TipoJuegoDtoRequest tipoJuegoDtoRequest, Long aLong) {
        if (tipoJuegoRepo.existsByNombreIgnoreCaseAndIdTipoJuegoIsNot(tipoJuegoDtoRequest.getNombre(), aLong)) {
            Map<String,String> errors = new HashMap<>();
            errors.put("nombre", tipoJuegoDtoRequest.getNombre());
            throw new AlreadyEntityExistException("tipo juego", errors);
        }

        return super.update(tipoJuegoDtoRequest, aLong);
    }
}
