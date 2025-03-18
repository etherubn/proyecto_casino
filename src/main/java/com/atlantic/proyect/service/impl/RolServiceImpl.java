package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.RolDtoRequest;
import com.atlantic.proyect.entity.Rol;
import com.atlantic.proyect.exception.ExceptionGeneric;
import com.atlantic.proyect.repository.RolRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IRolService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends CRUDImpl<Rol, RolDtoRequest,Long> implements IRolService {
    private final RolRepo rolRepo;

    public RolServiceImpl(MapperUtil mapperUtil, RolRepo rolRepo) {
        super(mapperUtil);
        this.rolRepo = rolRepo;
    }

    @Override
    protected GenericRepo<Rol, Long> genericRepo() {
        return rolRepo;
    }

    @Override
    protected Class<Rol> getEntityClass() {
        return Rol.class;
    }

    @Override
    protected Class<RolDtoRequest> getDTOClass() {
        return RolDtoRequest.class;
    }

    @Override
    protected void setId(Rol entity, Long aLong) {
        entity.setIdRol(aLong);
    }

    @Override
    public RolDtoRequest findRolByTipoRol(String tipoRol) {
        Rol rol = rolRepo.findOneByTipoRol_Nombre(tipoRol).orElseThrow(()-> new ExceptionGeneric("No existe el rol"));
        return mapperUtil.map(rol, RolDtoRequest.class);
    }
}
