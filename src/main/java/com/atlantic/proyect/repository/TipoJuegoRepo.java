package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.TipoJuego;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoJuegoRepo extends GenericRepo<TipoJuego, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdTipoJuegoIsNot(String nombre, Long idTipoJuego);
}
