package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.RolDtoRequest;
import com.atlantic.proyect.dto.request.create.TipoRolDtoRequest;
import com.atlantic.proyect.entity.Rol;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.exception.ExceptionGeneric;
import com.atlantic.proyect.repository.RolRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IRolService;
import com.atlantic.proyect.service.ITipoRolService;
import com.atlantic.proyect.utils.MapperUtil;
import com.atlantic.proyect.utils.MetodosString;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class RolServiceImpl extends CRUDImpl<Rol, RolDtoRequest,Long> implements IRolService {
    private final RolRepo rolRepo;
    private final ITipoRolService tipoRolService;
    private final MetodosString metodosString;

    public RolServiceImpl(MapperUtil mapperUtil, RolRepo rolRepo, ITipoRolService tipoRolService, MetodosString metodosString) {
        super(mapperUtil);
        this.rolRepo = rolRepo;
        this.tipoRolService = tipoRolService;
        this.metodosString = metodosString;
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
        Rol rol = rolRepo.findOneByTipoRol_NombreIgnoreCase(tipoRol).orElseThrow(()-> new ExceptionGeneric("No existe el rol"));
        return mapperUtil.map(rol, RolDtoRequest.class);
    }

    @Override
    @Transactional
    public RolDtoRequest createAnotherRol(RolDtoRequest rolDtoRequest) {
        String nombreFormateado = metodosString.formatearNombre(rolDtoRequest.getTipoRol().getNombre());
        rolRepo.findOneByTipoRol_NombreIgnoreCase(nombreFormateado).ifPresent(
                (rol)->{
                    Map<String,String> errors = new HashMap<>();
                    errors.put("nombre", rolDtoRequest.getTipoRol().getNombre());
                    throw new AlreadyEntityExistException("rol", errors);
                }
        );
        rolDtoRequest.getTipoRol().setNombre(nombreFormateado);
        return super.save(rolDtoRequest);
    }


    @Override
    @Transactional
    public RolDtoRequest save(RolDtoRequest rolDtoRequest) {
        String nombreFormateado = metodosString.formatearNombre(rolDtoRequest.getTipoRol().getNombre());
        if (tipoRolService.existByNombre(nombreFormateado)) {
            Map<String,String> errors = new HashMap<>();
            errors.put("tipoRol", rolDtoRequest.getTipoRol().getNombre());
            throw new AlreadyEntityExistException("tipoRol", errors);
        }

        rolDtoRequest.getTipoRol().setNombre(nombreFormateado);

        return super.save(rolDtoRequest);
    }

}
