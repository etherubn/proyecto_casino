package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepo extends GenericRepo<Persona, Long> {

    boolean existsByDni(String dni);
    boolean existsByTelefono(String telefono);

    @Query("SELECT " +
            "CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Persona p WHERE p.dni = :dni AND p.idPersona <> :id")
    boolean dniDiferenteId(String dni,Long id);

    @Query("SELECT " +
            "CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Persona p WHERE p.telefono = :dni AND p.idPersona <> :id")
    boolean telefonoDiferenteId(String telefono,Long id);


}
