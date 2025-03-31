package com.atlantic.proyect.dto.request.create;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class RolDtoRequest {
    @JsonProperty("id")
    private Long idRol;
    @Valid
    @NotNull(message = "El tipo de rol debe tener contenido")
    private TipoRolDtoRequest tipoRol;
}
