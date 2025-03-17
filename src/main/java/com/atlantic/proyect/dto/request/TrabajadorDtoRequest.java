package com.atlantic.proyect.dto.request;

import com.atlantic.proyect.entity.Usuario;
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
public class TrabajadorDtoRequest {
    @JsonProperty("id")
    private Long idTrabajador;
    @NotNull(message = "El area debe tener contenido")
    private AreaDtoRequest area;
    @NotNull(message = "el usuario debe tener contenido")
    private Usuario usuario;
}
