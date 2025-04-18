package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.Local;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalRepo extends GenericRepo<Local, Long> {
    boolean existsByNombreIgnoreCase(String nombre);

    boolean existsByNombreIgnoreCaseAndIdLocalIsNot(String nombre, Long idLocal);
}
