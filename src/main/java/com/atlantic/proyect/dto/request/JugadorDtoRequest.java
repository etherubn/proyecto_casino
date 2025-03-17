package com.atlantic.proyect.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JugadorDtoRequest {
    @JsonProperty("id")
    private Long idJugador;
    @NotNull(message = "El usuario es requerido")
    private UsuarioDtoRequest usuario;
}
