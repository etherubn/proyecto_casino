package com.atlantic.proyect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class RegistroJuego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRegistroJuego;
    @Column(nullable = false)
    private LocalDateTime fechaRegistroJuego;
    private Double ganancia;
    private Double perdida;

    @ManyToOne
    @JoinColumn(name = "id_tarjeta",nullable = false)
    private Tarjeta tarjeta;

    @ManyToOne
    @JoinColumn(name = "id_local",nullable = false)
    private Local local;

    @ManyToOne
    @JoinColumn(name = "id_juego",nullable = false)
    private Juego juego;

    @PrePersist
    private void onCreate() {
        fechaRegistroJuego = LocalDateTime.now();
    }

}
