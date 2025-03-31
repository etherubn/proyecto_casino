package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.JuegoDtoRequest;
import com.atlantic.proyect.dto.request.create.LocalDtoRequest;
import com.atlantic.proyect.dto.request.create.RegistroJuegoDtoRequest;
import com.atlantic.proyect.dto.request.create.TarjetaDtoRequest;
import com.atlantic.proyect.dto.request.update.TarjetaJugadaDtoRequest;
import com.atlantic.proyect.entity.RegistroJuego;
import com.atlantic.proyect.entity.Tarjeta;
import com.atlantic.proyect.exception.InsufficientFundsException;
import com.atlantic.proyect.repository.RegistroJuegoRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IJuegoService;
import com.atlantic.proyect.service.ILocalService;
import com.atlantic.proyect.service.IRegistroJuegoService;
import com.atlantic.proyect.service.ITarjetaService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroJuegoServiceImpl extends CRUDImpl<RegistroJuego, RegistroJuegoDtoRequest, Long> implements IRegistroJuegoService {
    private final RegistroJuegoRepo registroJuegoRepo;
    private final ITarjetaService tarjetaService;
    private final ILocalService localService;
    private final IJuegoService juegoService;

    public RegistroJuegoServiceImpl(MapperUtil mapperUtil, RegistroJuegoRepo registroJuegoRepo, ITarjetaService tarjetaService, ILocalService localService, IJuegoService juegoService) {
        super(mapperUtil);
        this.registroJuegoRepo = registroJuegoRepo;
        this.tarjetaService = tarjetaService;
        this.localService = localService;
        this.juegoService = juegoService;
    }

    @Override
    protected GenericRepo<RegistroJuego, Long> genericRepo() {
        return registroJuegoRepo;
    }

    @Override
    protected Class<RegistroJuego> getEntityClass() {
        return RegistroJuego.class;
    }

    @Override
    protected Class<RegistroJuegoDtoRequest> getDTOClass() {
        return RegistroJuegoDtoRequest.class;
    }

    @Override
    protected void setId(RegistroJuego entity, Long aLong) {
        entity.setIdRegistroJuego(aLong);
    }

    @Override
    @Transactional
    public RegistroJuegoDtoRequest jugar(RegistroJuegoDtoRequest registroJuegoDtoRequest) {
        double apuesta = registroJuegoDtoRequest.getMontoApuesta();
        TarjetaDtoRequest tarjetaDtoRequest = tarjetaService.findById(registroJuegoDtoRequest.getTarjeta().getIdTarjeta());

        if (apuesta>tarjetaDtoRequest.getMonto()){
            throw new InsufficientFundsException("No tiene fondos para jugar");
        }
        JuegoDtoRequest juegoDtoRequest = juegoService.findById(registroJuegoDtoRequest.getJuego().getIdJuego());
        LocalDtoRequest localDtoRequest = localService.findById(registroJuegoDtoRequest.getLocal().getIdLocal());

        registroJuegoDtoRequest.setTarjeta(tarjetaDtoRequest);
        registroJuegoDtoRequest.setJuego(juegoDtoRequest);
        registroJuegoDtoRequest.setLocal(localDtoRequest);

        RegistroJuego registroJuego = mapperUtil.map(registroJuegoDtoRequest, RegistroJuego.class);

        boolean ganoJugada = isGanoJugada();
        String codigo = tarjetaDtoRequest.getCodigo();
        TarjetaJugadaDtoRequest tarjetaJugadaDtoRequest = new TarjetaJugadaDtoRequest();



        tarjetaJugadaDtoRequest.setGanancia(ganoJugada ? apuesta : apuesta * -1);
        TarjetaDtoRequest tarjetaDtoRequest1 = tarjetaService.completarJugada(codigo, tarjetaJugadaDtoRequest);
        registroJuego.setTarjeta(mapperUtil.map(tarjetaDtoRequest1, Tarjeta.class));

        if (ganoJugada) {
            registroJuego.setGanancia(apuesta);
        } else {
            registroJuego.setPerdida(apuesta);
        }

        return mapperUtil.map(registroJuegoRepo.save(registroJuego), RegistroJuegoDtoRequest.class);

    }

    private boolean isGanoJugada() {
        return Math.random() > 0.5;
    }


}
