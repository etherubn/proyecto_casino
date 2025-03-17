package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.TipoRolDtoRequest;
import com.atlantic.proyect.entity.TipoRol;
import com.atlantic.proyect.repository.TipoRolRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.ITipoRolService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class TipoRolServiceImpl extends CRUDImpl<TipoRol, TipoRolDtoRequest,Long> implements ITipoRolService {
    private final TipoRolRepo tipoRolRepo;

    public TipoRolServiceImpl(MapperUtil mapperUtil, TipoRolRepo tipoRolRepo) {
        super(mapperUtil);
        this.tipoRolRepo = tipoRolRepo;
    }

    @Override
    protected GenericRepo<TipoRol, Long> genericRepo() {
        return tipoRolRepo;
    }

    @Override
    protected Class<TipoRol> getEntityClass() {
        return TipoRol.class;
    }

    @Override
    protected Class<TipoRolDtoRequest> getDTOClass() {
        return TipoRolDtoRequest.class;
    }

    @Override
    protected void setId(TipoRol entity, Long aLong) {
        entity.setIdTipoRol(aLong);
    }
}
