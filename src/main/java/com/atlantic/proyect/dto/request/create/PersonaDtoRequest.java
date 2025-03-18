package com.atlantic.proyect.dto.request.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonaDtoRequest {
    @JsonProperty("id")
    private Long idPersona;
    @NotBlank(message = "El nombre debe tener contenido")
    @Size(min = 3,message = "El nombre debe tener minimo 3 caracteres")
    private String nombre;
    @NotBlank(message = "El apellido paterno debe tener contenido")
    @Size(min = 3,message = "El apellido paterno debe tener minimo 3 caracteres")
    private String apellidoPaterno;
    @NotBlank(message = "El nombre debe tener contenido")
    @Size(min = 3,message = "El nombre debe tener minimo 3 caracteres")
    private String apellidoMaterno;
    @Pattern(regexp = "^\\d{8}$", message = "El DNI debe tener exactamente 8 dígitos.")
    @NotBlank(message = "El dni debe tener contenido")
    private String dni;
    @NotNull(message = "El genero debe tener contenido")
    private Boolean genero;
    @Pattern(regexp = "^9\\d{8}$", message = "El número de celular debe comenzar con 9 y tener 9 dígitos.")
    @NotBlank(message = "El telefono debe tener contenido")
    private String telefono;
    @Size(min = 8,message = "La direccion debe tener minimo 8 caracteres")
    private String direccion;
    @NotNull(message = "LA fecha de nacimiento debe tener contenido")
    private LocalDate fechaNacimiento;
}
