package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.RegistroJuegoDtoRequest;
import com.atlantic.proyect.entity.RegistroJuego;
import com.atlantic.proyect.repository.RegistroJuegoRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IRegistroJuegoService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class RegistroJuegoServiceImpl extends CRUDImpl<RegistroJuego, RegistroJuegoDtoRequest,Long> implements IRegistroJuegoService {
    private final RegistroJuegoRepo registroJuegoRepo;

    public RegistroJuegoServiceImpl(MapperUtil mapperUtil, RegistroJuegoRepo registroJuegoRepo) {
        super(mapperUtil);
        this.registroJuegoRepo = registroJuegoRepo;
    }

    @Override
    protected GenericRepo<RegistroJuego, Long> genericRepo() {
        return registroJuegoRepo;
    }

    @Override
    protected Class<RegistroJuego> getEntityClass() {
        return RegistroJuego.class;
    }

    @Override
    protected Class<RegistroJuegoDtoRequest> getDTOClass() {
        return RegistroJuegoDtoRequest.class;
    }

    @Override
    protected void setId(RegistroJuego entity, Long aLong) {
        entity.setIdRegistroJuego(aLong);
    }
}
