package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.JugadorDtoRequest;
import com.atlantic.proyect.dto.request.UsuarioDtoRequest;
import com.atlantic.proyect.entity.Jugador;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.repository.JugadorRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IJugadorService;
import com.atlantic.proyect.service.IUsuarioService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class JugadorServiceImpl extends CRUDImpl<Jugador, JugadorDtoRequest,Long> implements IJugadorService {
    private final JugadorRepo jugadorRepo;
    private final IUsuarioService usuarioService;


    public JugadorServiceImpl(MapperUtil mapperUtil, JugadorRepo jugadorRepo, IUsuarioService usuarioService) {
        super(mapperUtil);
        this.jugadorRepo = jugadorRepo;
        this.usuarioService = usuarioService;
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
    public JugadorDtoRequest crearJugador(JugadorDtoRequest jugadorDtoRequest) {

        Map<String,String> errors = usuarioService.verifyCreateUsernameAndPerson(jugadorDtoRequest.getUsuario());

        if (!errors.isEmpty()) {
            System.out.println(errors);
            throw new AlreadyEntityExistException("jugador", errors);
        }


        UsuarioDtoRequest usuarioDtoRequest = usuarioService.save(jugadorDtoRequest.getUsuario());
        jugadorDtoRequest.setUsuario(usuarioDtoRequest);

        Jugador createdJugador = jugadorRepo.save(mapperUtil.map(jugadorDtoRequest, Jugador.class));


        return mapperUtil.map(createdJugador, JugadorDtoRequest.class);
    }
}
