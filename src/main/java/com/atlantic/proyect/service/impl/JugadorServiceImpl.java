package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.JugadorDtoRequest;
import com.atlantic.proyect.dto.request.create.RolDtoRequest;
import com.atlantic.proyect.dto.request.create.UsuarioDtoRequest;
import com.atlantic.proyect.dto.request.update.JugadorUpdateDtoRequest;
import com.atlantic.proyect.entity.Jugador;
import com.atlantic.proyect.entity.Rol;
import com.atlantic.proyect.entity.Usuario;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.exception.ModelNotFoundException;
import com.atlantic.proyect.repository.JugadorRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IJugadorService;
import com.atlantic.proyect.service.IRolService;
import com.atlantic.proyect.service.IUsuarioService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class JugadorServiceImpl extends CRUDImpl<Jugador, JugadorDtoRequest,Long> implements IJugadorService {
    private final JugadorRepo jugadorRepo;
    private final IUsuarioService usuarioService;
    private final IRolService rolService;


    public JugadorServiceImpl(MapperUtil mapperUtil, JugadorRepo jugadorRepo, IUsuarioService usuarioService, IRolService rolService) {
        super(mapperUtil);
        this.jugadorRepo = jugadorRepo;
        this.usuarioService = usuarioService;
        this.rolService = rolService;
    }

    @Override
    protected GenericRepo<Jugador, Long> genericRepo() {
        return jugadorRepo;
    }

    @Override
    protected Class<Jugador> getEntityClass() {
        return Jugador.class;
    }

    @Override
    protected Class<JugadorDtoRequest> getDTOClass() {
        return JugadorDtoRequest.class;
    }

    @Override
    protected void setId(Jugador entity, Long aLong) {
        entity.setIdJugador(aLong);
    }


    @Override
    @Transactional
    public JugadorDtoRequest crearJugador(JugadorDtoRequest jugadorDtoRequest) {

        Map<String,String> errors = usuarioService.verifyCreateUsernameAndPerson(jugadorDtoRequest.getUsuario());

        if (!errors.isEmpty()) {
            throw new AlreadyEntityExistException("jugador", errors);
        }

        Set<RolDtoRequest> rolesDto = Set.of(rolService.findRolByTipoRol("USER"));
        jugadorDtoRequest.getUsuario().setRoles(rolesDto);



        return mapperUtil.map(save(jugadorDtoRequest), JugadorDtoRequest.class);
    }

    @Override
    @Transactional
    public JugadorDtoRequest actualizarJugador(JugadorUpdateDtoRequest jugadorUpdateDtoRequest,Long id) {
        Jugador jugador = jugadorRepo.findById(id)
                .orElseThrow(()-> new ModelNotFoundException("jugador"));

        Map<String,String> errors = usuarioService.verifyUpdateUsernameAndPerson(jugadorUpdateDtoRequest.getUsuario());

        if (!errors.isEmpty()) {
            throw new AlreadyEntityExistException("jugador", errors);
        }

        Optional.ofNullable(jugadorUpdateDtoRequest.getUsuario())
                .ifPresent(usuario->{
                    UsuarioDtoRequest usuarioDtoRequest = usuarioService.update(usuario,id);
                    jugador.setUsuario(mapperUtil.map(usuarioDtoRequest, Usuario.class));
                });

        return mapperUtil.map(jugadorRepo.save(jugador), JugadorDtoRequest.class);
    }
}
