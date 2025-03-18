package com.atlantic.proyect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Tarjeta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTarjeta;
    @Column(nullable = false, unique = true)
    private String codigo;
    @Column(nullable = false)
    private Double monto;
    @OneToOne
    @JoinColumn(name = "id_jugador",nullable = false,unique = true,foreignKey = @ForeignKey(name = "FK_TARJETA_JUGADOR"))
    private Jugador jugador;


}
