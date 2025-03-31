package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.Area;
import org.springframework.stereotype.Repository;

@Repository
public interface AreaRepo extends GenericRepo<Area, Long> {
    boolean existsByNombreIgnoreCase(String nombre);
}
