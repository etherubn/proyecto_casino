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
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idJuego;

    @Column(nullable = false,unique = true)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_tipo_juego",nullable = false,foreignKey = @ForeignKey(name = "FK_JUEGO_TIPOJUEGO"))
    private TipoJuego tipoJuego;

}
