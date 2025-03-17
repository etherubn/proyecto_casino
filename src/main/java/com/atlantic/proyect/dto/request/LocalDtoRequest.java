package com.atlantic.proyect.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LocalDtoRequest {
    @JsonProperty("id")
    private Long idLocal;
    @NotBlank(message = "El nombre debe tener contenido")
    private String nombre;
}
