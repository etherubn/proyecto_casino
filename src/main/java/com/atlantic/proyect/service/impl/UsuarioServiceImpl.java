package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.PersonaDtoRequest;
import com.atlantic.proyect.dto.request.create.UsuarioDtoRequest;
import com.atlantic.proyect.dto.request.update.UsuarioUpdateDtoRequest;
import com.atlantic.proyect.entity.Persona;
import com.atlantic.proyect.entity.Rol;
import com.atlantic.proyect.entity.Usuario;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.exception.ModelNotFoundException;
import com.atlantic.proyect.repository.UsuarioRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IPersonaService;
import com.atlantic.proyect.service.IRolService;
import com.atlantic.proyect.service.IUsuarioService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class UsuarioServiceImpl extends CRUDImpl<Usuario, UsuarioDtoRequest, Long> implements IUsuarioService {
    private final UsuarioRepo usuarioRepo;
    private final IPersonaService personaService;
    private final IRolService rolService;

    public UsuarioServiceImpl(MapperUtil mapperUtil, UsuarioRepo usuarioRepo, IPersonaService personaService, IRolService rolService) {
        super(mapperUtil);
        this.usuarioRepo = usuarioRepo;
        this.personaService = personaService;
        this.rolService = rolService;
    }

    @Override
    protected GenericRepo<Usuario, Long> genericRepo() {
        return usuarioRepo;
    }

    @Override
    protected Class<Usuario> getEntityClass() {
        return Usuario.class;
    }

    @Override
    protected Class<UsuarioDtoRequest> getDTOClass() {
        return UsuarioDtoRequest.class;
    }

    @Override
    protected void setId(Usuario entity, Long aLong) {
        entity.setIdUsuario(aLong);
    }

    @Override
    @Transactional
    public UsuarioDtoRequest save(UsuarioDtoRequest usuarioDtoRequest) {

        PersonaDtoRequest personaDtoRequest = personaService.save(usuarioDtoRequest.getPersona());
        usuarioDtoRequest.setPersona(personaDtoRequest);

        Persona persona = mapperUtil.map(personaDtoRequest, Persona.class);
        Usuario usuario = mapperUtil.map(usuarioDtoRequest, Usuario.class);
        usuario.setPersona(persona);

        Set<Rol> roles = new HashSet<>();
        Rol rol = mapperUtil.map(rolService.findRolByTipoRol("USER"), Rol.class);
        roles.add(rol);
        usuario.setRoles(roles);

        usuario = usuarioRepo.save(usuario);

        return mapperUtil.map(usuario, UsuarioDtoRequest.class);
    }


    @Transactional
    public UsuarioDtoRequest update(UsuarioUpdateDtoRequest usuarioUpdateDtoRequest, Long aLong) {
        Usuario usuario = usuarioRepo.findById(aLong).orElseThrow(() -> new ModelNotFoundException("usuario"));
        Optional.ofNullable(usuarioUpdateDtoRequest.getActivo())
                .ifPresent(activo -> usuario.setActivo(activo));

        Optional.ofNullable(usuarioUpdateDtoRequest.getUsername())
                .ifPresent(username -> usuario.setUsername(username));

        Optional.ofNullable(usuarioUpdateDtoRequest.getFoto())
                .ifPresent(foto -> usuario.setFoto(foto));

        Optional.ofNullable(usuarioUpdateDtoRequest.getPersona())
                .ifPresent(persona -> {
                    PersonaDtoRequest personaDtoRequest = personaService.update(persona, aLong);
                    usuario.setPersona(mapperUtil.map(personaDtoRequest, Persona.class));
                });

        return mapperUtil.map(usuarioRepo.save(usuario), UsuarioDtoRequest.class);
    }

    public Map<String, String> verifyCreateUsernameAndPerson(UsuarioDtoRequest usuarioDtoRequest) {
        String username = usuarioDtoRequest.getUsername();

        Map<String, String> personaErrors = personaService.verifyCreateDniAndTelefono(usuarioDtoRequest.getPersona());
        Map<String, String> usuarioErrors = new HashMap<>();

        if (usuarioRepo.existsByUsername(username)) {
            usuarioErrors.put("username", username);
        }

        return Stream.of(personaErrors, usuarioErrors)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

    }

    public Map<String, String> verifyUpdateUsernameAndPerson(UsuarioUpdateDtoRequest usuarioUpdateDtoRequest) {
        String username = usuarioUpdateDtoRequest.getUsername();
        Map<String, String> personaErrors = personaService.verifyUpdateDniAndTelefono(usuarioUpdateDtoRequest.getPersona());
        Map<String, String> usuarioErrors = new HashMap<>();

        if (usuarioRepo.existsByUsernameAndIdUsuarioIsNot(username, usuarioUpdateDtoRequest.getIdUsuario())) {
            usuarioErrors.put("username", username);
        }

        return Stream.of(personaErrors, usuarioErrors)
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> entry.getValue()));

    }


}
