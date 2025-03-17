package com.atlantic.proyect.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AreaDtoRequest {
    @JsonProperty("id")
    private Long idArea;
    @NotBlank(message = "El nombre debe tener contenido")
    @Size(min =3,message = "Debe tener más de 2 letras")
    private String nombre;
}
