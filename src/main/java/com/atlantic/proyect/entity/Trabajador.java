package com.atlantic.proyect.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTrabajador;
    @ManyToOne
    @JoinColumn(nullable = false,name = "id_area",foreignKey = @ForeignKey(name = "FK_TRABAJADOR_AREA"))
    private Area area;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}
