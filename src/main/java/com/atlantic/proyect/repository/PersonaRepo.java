package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.Persona;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends GenericRepo<Persona, Long> {
}
