package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.Rol;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepo extends GenericRepo<Rol, Long> {
    Optional<Rol> findOneByTipoRol_Nombre(String nombre);
}
