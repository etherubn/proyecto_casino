package com.atlantic.proyect.dto.request.update;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsuarioUpdateDtoRequest {
    @JsonProperty("id")
    private Long idUsuario;
    private Boolean activo;
    @Size(min = 3,max = 16,message = "El username debe tener entre 3 y 16 caracteres")
    private String username;
    private String foto;
    @Valid
    private PersonaUpdateDtoRequest persona;
}
