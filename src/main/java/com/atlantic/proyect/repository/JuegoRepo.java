package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.Juego;
import org.springframework.stereotype.Repository;

@Repository
public interface JuegoRepo extends GenericRepo<Juego, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
    boolean existsByNombreIgnoreCaseAndIdJuegoIsNot(String nombre, Long idJuego);
}
