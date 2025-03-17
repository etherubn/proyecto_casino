package com.atlantic.proyect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidoPaterno;
    @Column(nullable = false)
    private String apellidoMaterno;
    @Column(unique = true, nullable = false,length =8 )
    private String dni;
    @Column(nullable = false)
    private Boolean genero;
    @Column(nullable = false,unique = true,length =9 )
    private String telefono;
    @Column(nullable = false)
    private String direccion;
    @Column(nullable = false)
    private LocalDate fechaNacimiento;
}
