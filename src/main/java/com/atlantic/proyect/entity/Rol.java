package com.atlantic.proyect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idRol;

    @OneToOne
    @JoinColumn(name = "id_tipo_rol",nullable = false,foreignKey = @ForeignKey(name = "FK_ROL_TIPOROL"),unique = true)
    private TipoRol tipoRol;
}
