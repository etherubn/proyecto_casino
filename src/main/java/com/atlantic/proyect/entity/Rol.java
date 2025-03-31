package com.atlantic.proyect.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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

    @OneToOne(cascade =CascadeType.ALL)
    @JoinColumn(name = "id_tipo_rol",foreignKey = @ForeignKey(name = "FK_ROL_TIPOROL"))
    private TipoRol tipoRol;
}
