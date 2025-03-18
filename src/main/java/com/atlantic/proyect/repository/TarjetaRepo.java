package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.Tarjeta;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TarjetaRepo extends GenericRepo<Tarjeta, Long> {
    Optional<Tarjeta> findByCodigo(String codigo);
}
