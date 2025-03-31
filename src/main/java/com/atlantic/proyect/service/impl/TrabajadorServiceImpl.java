package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.AreaDtoRequest;
import com.atlantic.proyect.dto.request.create.RolDtoRequest;
import com.atlantic.proyect.dto.request.create.TrabajadorDtoRequest;
import com.atlantic.proyect.dto.request.create.UsuarioDtoRequest;
import com.atlantic.proyect.entity.Rol;
import com.atlantic.proyect.entity.Trabajador;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.repository.TrabajadorRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IAreaService;
import com.atlantic.proyect.service.IRolService;
import com.atlantic.proyect.service.ITrabajadorService;
import com.atlantic.proyect.service.IUsuarioService;
import com.atlantic.proyect.utils.MapperUtil;
import com.atlantic.proyect.utils.MetodosString;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class TrabajadorServiceImpl extends CRUDImpl<Trabajador, TrabajadorDtoRequest,Long> implements ITrabajadorService {
    private final TrabajadorRepo trabajadorRepo;
    private final IUsuarioService usuarioService;
    private final IAreaService areaService;
    private final IRolService rolService;
    private final MetodosString metodosString;

    public TrabajadorServiceImpl(MapperUtil mapperUtil, TrabajadorRepo trabajadorRepo, IUsuarioService usuarioService, IAreaService areaService, IRolService rolService, MetodosString metodosString) {
        super(mapperUtil);
        this.trabajadorRepo = trabajadorRepo;
        this.usuarioService = usuarioService;
        this.areaService = areaService;
        this.rolService = rolService;
        this.metodosString = metodosString;
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

    @Override
    public TrabajadorDtoRequest save(TrabajadorDtoRequest trabajadorDtoRequest) {
        AreaDtoRequest areaDtoRequest = areaService.findById(trabajadorDtoRequest.getArea().getIdArea());
        String nombreFormateado = metodosString.formatearNombre(areaDtoRequest.getNombre());
        RolDtoRequest rolDtoRequest = rolService.findRolByTipoRol(nombreFormateado);


        Set<RolDtoRequest> rolesDto = new HashSet<>();
        rolesDto.add(rolService.findRolByTipoRol("USER"));
        rolesDto.add(rolDtoRequest);

        trabajadorDtoRequest.getUsuario().setRoles(rolesDto);
        trabajadorDtoRequest.setArea(areaDtoRequest);

        return super.save(trabajadorDtoRequest);
    }



}
