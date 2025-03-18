package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.TrabajadorDtoRequest;
import com.atlantic.proyect.entity.Trabajador;
import com.atlantic.proyect.repository.TrabajadorRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.ITrabajadorService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class TrabajadorServiceImpl extends CRUDImpl<Trabajador, TrabajadorDtoRequest,Long> implements ITrabajadorService {
    private final TrabajadorRepo trabajadorRepo;

    public TrabajadorServiceImpl(MapperUtil mapperUtil, TrabajadorRepo trabajadorRepo) {
        super(mapperUtil);
        this.trabajadorRepo = trabajadorRepo;
    }

    @Override
    protected GenericRepo<Trabajador, Long> genericRepo() {
        return trabajadorRepo;
    }

    @Override
    protected Class<Trabajador> getEntityClass() {
        return Trabajador.class;
    }

    @Override
    protected Class<TrabajadorDtoRequest> getDTOClass() {
        return TrabajadorDtoRequest.class;
    }

    @Override
    protected void setId(Trabajador entity, Long aLong) {
        entity.setIdTrabajador(aLong);
    }


}
