package com.atlantic.proyect.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioDtoRequest {
    @JsonProperty("id")
    private Long idUsuario;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDate fechaRegistro;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private boolean activo;
    @NotBlank(message = "El username debe tener contenido")
    @Size(min = 3,max = 16,message = "El username debe tener entre 3 y 16 caracteres")
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,12}$", message = "Debe contener solo número y letras.")
    @Size(min = 6,max = 12,message = "La contraseña debe tener entre 6 y 12 caracteres")
    private String password;
    private String foto;
    private PersonaDtoRequest persona;
    private Set<RolDtoRequest> roles;
}
