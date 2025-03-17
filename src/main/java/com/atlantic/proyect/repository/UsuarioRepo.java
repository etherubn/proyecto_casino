package com.atlantic.proyect.repository;

import com.atlantic.proyect.entity.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepo extends GenericRepo<Usuario, Long> {
}
