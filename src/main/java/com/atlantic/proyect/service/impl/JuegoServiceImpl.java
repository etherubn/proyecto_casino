package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.JuegoDtoRequest;
import com.atlantic.proyect.dto.request.create.TipoJuegoDtoRequest;
import com.atlantic.proyect.dto.request.update.JuegoUpdateDtoRequest;
import com.atlantic.proyect.entity.Juego;
import com.atlantic.proyect.entity.TipoJuego;
import com.atlantic.proyect.exception.AlreadyEntityExistException;
import com.atlantic.proyect.exception.ModelNotFoundException;
import com.atlantic.proyect.repository.JuegoRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IJuegoService;
import com.atlantic.proyect.service.ITipoJuegoService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class JuegoServiceImpl extends CRUDImpl<Juego, JuegoDtoRequest, Long> implements IJuegoService {
    private final JuegoRepo juegoRepo;
    private final ITipoJuegoService tipoJuegoService;

    public JuegoServiceImpl(MapperUtil mapperUtil, JuegoRepo juegoRepo, ITipoJuegoService tipoJuegoService) {
        super(mapperUtil);
        this.juegoRepo = juegoRepo;
        this.tipoJuegoService = tipoJuegoService;
    }

    @Override
    protected GenericRepo<Juego, Long> genericRepo() {
        return juegoRepo;
    }

    @Override
    protected Class<Juego> getEntityClass() {
        return Juego.class;
    }

    @Override
    protected Class<JuegoDtoRequest> getDTOClass() {
        return JuegoDtoRequest.class;
    }

    @Override
    protected void setId(Juego entity, Long aLong) {
        entity.setIdJuego(aLong);
    }

    @Override
    @Transactional
    public JuegoDtoRequest save(JuegoDtoRequest juegoDtoRequest) {
        if (juegoRepo.existsByNombreIgnoreCase(juegoDtoRequest.getNombre())) {
            Map<String, String> errors = new HashMap<>();
            errors.put("nombre", juegoDtoRequest.getNombre());
            throw new AlreadyEntityExistException("juego", errors);
        }

        TipoJuegoDtoRequest tipoJuegoDtoRequest = tipoJuegoService.findById(juegoDtoRequest.getTipoJuego().getIdTipoJuego());
        TipoJuego tipoJuego = mapperUtil.map(tipoJuegoDtoRequest, TipoJuego.class);

        Juego juego = mapperUtil.map(juegoDtoRequest, Juego.class);
        juego.setTipoJuego(tipoJuego);

        return mapperUtil.map(juegoRepo.save(juego), JuegoDtoRequest.class);
    }

    @Transactional
    public JuegoDtoRequest update(JuegoUpdateDtoRequest juegoUpdateDtoRequest, Long aLong) {
        Juego juego = juegoRepo.findById(aLong).orElseThrow(() -> new ModelNotFoundException("juego"));
        if (juegoRepo.existsByNombreIgnoreCaseAndIdJuegoIsNot(juegoUpdateDtoRequest.getNombre(), aLong)) {
            Map<String, String> errors = new HashMap<>();
            errors.put("nombre", juegoUpdateDtoRequest.getNombre());
            throw new AlreadyEntityExistException("juego", errors);
        }


        String nombre = juegoUpdateDtoRequest.getNombre();
        TipoJuegoDtoRequest tipoJuegoDtoRequest = juegoUpdateDtoRequest.getTipoJuego();

        Optional.ofNullable(nombre).filter(nombreExisted -> !juegoRepo.existsByNombreIgnoreCase(nombreExisted))
                .ifPresentOrElse(
                        data -> juego.setNombre(data),
                        () -> {
                            Map<String, String> errors = new HashMap<>();
                            errors.put("nombre", juegoUpdateDtoRequest.getNombre());
                            throw new AlreadyEntityExistException("juego", errors);
                        }
                );
        Optional.ofNullable(tipoJuegoDtoRequest).ifPresent(tipoJuego -> {
            TipoJuegoDtoRequest tipoJuegoDtoRequest1 = tipoJuegoService.findById(tipoJuego.getIdTipoJuego());
            juego.setTipoJuego(mapperUtil.map(tipoJuegoDtoRequest1, TipoJuego.class));
        });

        return mapperUtil.map(juegoRepo.save(juego), JuegoDtoRequest.class);
    }
}
