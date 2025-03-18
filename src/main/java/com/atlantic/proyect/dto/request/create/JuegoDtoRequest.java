package com.atlantic.proyect.dto.request.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JuegoDtoRequest {
    @JsonProperty("id")
    private Long idJuego;
    @NotBlank(message = "El nombre debe tener contenido")
    @Size(min =3,message = "Debe tener más de 2 letras")
    private String nombre;
    @NotNull
    private TipoJuegoDtoRequest tipoJuego;
}
