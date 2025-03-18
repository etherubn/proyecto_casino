package com.atlantic.proyect.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column(nullable = false)
    private LocalDate fechaRegistro;
    @Column(nullable = false)
    private Boolean activo;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    private String foto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_persona",foreignKey = @ForeignKey(name = "FK_USUARIO_PERSONA"),nullable = false)
    private Persona persona;

    @ManyToMany
    @JoinTable(name = "usuario_rol",
        joinColumns = @JoinColumn(name = "id_usuario",referencedColumnName = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol",referencedColumnName = "idRol")
    )
    private Set<Rol> roles;

    @PrePersist
    public void prePersist() {
        this.fechaRegistro = LocalDate.now();
        activo = true;
    }
}
