package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.TarjetaDtoRequest;
import com.atlantic.proyect.entity.Tarjeta;
import com.atlantic.proyect.repository.TarjetaRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.ITarjetaService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class TarjetaServiceImpl extends CRUDImpl<Tarjeta, TarjetaDtoRequest,Long> implements ITarjetaService {
    private final TarjetaRepo tarjetaRepo;

    public TarjetaServiceImpl(MapperUtil mapperUtil, TarjetaRepo tarjetaRepo) {
        super(mapperUtil);
        this.tarjetaRepo = tarjetaRepo;
    }

    @Override
    protected GenericRepo<Tarjeta, Long> genericRepo() {
        return tarjetaRepo;
    }

    @Override
    protected Class<Tarjeta> getEntityClass() {
        return Tarjeta.class;
    }

    @Override
    protected Class<TarjetaDtoRequest> getDTOClass() {
        return TarjetaDtoRequest.class;
    }

    @Override
    protected void setId(Tarjeta entity, Long aLong) {
        entity.setIdTarjeta(aLong);
    }
}
