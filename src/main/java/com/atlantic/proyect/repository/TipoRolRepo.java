package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.TipoRol;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoRolRepo extends GenericRepo<TipoRol, Long> {
    Boolean existsByNombre(String nombre);
}
