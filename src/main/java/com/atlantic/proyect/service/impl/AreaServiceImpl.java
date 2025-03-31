package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.AreaDtoRequest;
import com.atlantic.proyect.dto.request.create.RolDtoRequest;
import com.atlantic.proyect.dto.request.create.TipoRolDtoRequest;
import com.atlantic.proyect.entity.Area;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.repository.AreaRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IAreaService;
import com.atlantic.proyect.service.IRolService;
import com.atlantic.proyect.utils.MapperUtil;
import com.atlantic.proyect.utils.MetodosString;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class AreaServiceImpl extends CRUDImpl<Area, AreaDtoRequest,Long> implements IAreaService {
    private final AreaRepo areaRepo;
    private final IRolService rolService;
    private final MetodosString metodosString;

    public AreaServiceImpl(MapperUtil mapperUtil, AreaRepo areaRepo, IRolService rolService, MetodosString metodosString) {
        super(mapperUtil);
        this.areaRepo = areaRepo;
        this.rolService = rolService;
        this.metodosString = metodosString;
    }

    @Override
    protected GenericRepo<Area, Long> genericRepo() {
        return areaRepo;
    }

    @Override
    protected Class<Area> getEntityClass() {
        return Area.class;
    }

    @Override
    protected Class<AreaDtoRequest> getDTOClass() {
        return AreaDtoRequest.class;
    }

    @Override
    protected void setId(Area entity, Long aLong) {
        entity.setIdArea(aLong);
    }

    @Override
    public AreaDtoRequest save(AreaDtoRequest areaDtoRequest) {
        if(areaRepo.existsByNombreIgnoreCase(areaDtoRequest.getNombre())){
            Map<String,String> errors = new HashMap<>();
            errors.put("nombre", areaDtoRequest.getNombre());
            throw new AlreadyEntityExistException("nombre",errors);
        }

        RolDtoRequest rolDtoRequest = new RolDtoRequest();
        TipoRolDtoRequest tipoRolDtoRequest = new TipoRolDtoRequest();
        String nombreFormateado = metodosString.formatearNombre(areaDtoRequest.getNombre());
        tipoRolDtoRequest.setNombre(nombreFormateado);
        rolDtoRequest.setTipoRol(tipoRolDtoRequest);

        rolService.save(rolDtoRequest);

        return super.save(areaDtoRequest);
    }
}
