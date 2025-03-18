package com.atlantic.proyect.dto.request.update;

import com.atlantic.proyect.dto.request.create.UsuarioDtoRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JugadorUpdateDtoRequest {
    @JsonProperty("id")
    private Long idJugador;
    @Valid
    private UsuarioUpdateDtoRequest usuario;
}
