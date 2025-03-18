package com.atlantic.proyect.service.impl;

import com.atlantic.proyect.dto.request.create.JugadorDtoRequest;
import com.atlantic.proyect.dto.request.create.TarjetaDtoRequest;
import com.atlantic.proyect.dto.request.update.TarjetaJugadaDtoRequest;
import com.atlantic.proyect.dto.request.update.TarjetaRecargaDtoRequest;
import com.atlantic.proyect.entity.Tarjeta;
import com.atlantic.proyect.exception.ExceptionGeneric;
import com.atlantic.proyect.repository.TarjetaRepo;
import com.atlantic.proyect.repository.GenericRepo;
import com.atlantic.proyect.service.IJugadorService;
import com.atlantic.proyect.service.ITarjetaService;
import com.atlantic.proyect.utils.MapperUtil;
import org.springframework.stereotype.Service;

@Service
public class TarjetaServiceImpl extends CRUDImpl<Tarjeta, TarjetaDtoRequest, Long> implements ITarjetaService {
    private final TarjetaRepo tarjetaRepo;
    private final IJugadorService jugadorService;

    public TarjetaServiceImpl(MapperUtil mapperUtil, TarjetaRepo tarjetaRepo, IJugadorService jugadorService) {
        super(mapperUtil);
        this.tarjetaRepo = tarjetaRepo;
        this.jugadorService = jugadorService;
    }

    @Override
    protected GenericRepo<Tarjeta, Long> genericRepo() {
        return tarjetaRepo;
    }

    @Override
    protected Class<Tarjeta> getEntityClass() {
        return Tarjeta.class;
    }

    @Override
    protected Class<TarjetaDtoRequest> getDTOClass() {
        return TarjetaDtoRequest.class;
    }

    @Override
    protected void setId(Tarjeta entity, Long aLong) {
        entity.setIdTarjeta(aLong);
    }

    @Override
    public TarjetaDtoRequest save(TarjetaDtoRequest tarjetaDtoRequest) {
        JugadorDtoRequest jugadorDtoRequest = jugadorService.findById(tarjetaDtoRequest.getJugador().getIdJugador());

        String dni = jugadorDtoRequest.getUsuario().getPersona().getDni();
        String name = jugadorDtoRequest.getUsuario().getPersona().getNombre();
        String lastName = jugadorDtoRequest.getUsuario().getPersona().getApellidoPaterno();

        if (dni == null || name == null || lastName == null) {
            throw new IllegalArgumentException("dni, nombre o apellido no puede ser nulo");
        }

        char initialLetterName = name.charAt(0);
        char initialLetterLastName = lastName.charAt(0);
        String codigo = dni.concat(String.valueOf(initialLetterName)).concat(String.valueOf(initialLetterLastName)).toUpperCase();

        tarjetaDtoRequest.setCodigo(codigo);
        tarjetaDtoRequest.setJugador(jugadorDtoRequest);

        return super.save(tarjetaDtoRequest);
    }


    @Override
    public TarjetaDtoRequest recargarTarjeta(String codigo,TarjetaRecargaDtoRequest tarjetaRecargaDtoRequest) {
        validarCodigo(codigo);

        Tarjeta tarjeta = tarjetaRepo.findByCodigo(codigo)
                .orElseThrow(()-> new ExceptionGeneric("Tarjeta no encontrada"));

        double saldoActual = tarjetaRecargaDtoRequest.getSaldo()+tarjeta.getMonto();
        tarjeta.setMonto(saldoActual);
        return mapperUtil.map(tarjetaRepo.save(tarjeta), TarjetaDtoRequest.class);
    }

    @Override
    public TarjetaDtoRequest completarJugada(String codigo,TarjetaJugadaDtoRequest tarjetaJugadaDtoRequest) {
        validarCodigo(codigo);

        double ganancia = tarjetaJugadaDtoRequest.getGanancia();

        if (ganancia==0){
            throw new ExceptionGeneric("La ganancia debe ser positivo");
        }

        Tarjeta tarjeta = tarjetaRepo.findByCodigo(codigo)
                .orElseThrow(()-> new ExceptionGeneric("Tarjeta no encontrada"));
        double saldoFinal = tarjeta.getMonto()+ganancia;
        tarjeta.setMonto(saldoFinal);
        return mapperUtil.map(tarjetaRepo.save(tarjeta), TarjetaDtoRequest.class);
    }

    private  void validarCodigo(String codigo) {
        if (codigo.isBlank()) {
            throw new ExceptionGeneric("codigo debe tener contenido");
        }

        if (codigo.length() != 10) {
            throw new ExceptionGeneric("codigo debe tener 10 caracteres");
        }
    }
}
