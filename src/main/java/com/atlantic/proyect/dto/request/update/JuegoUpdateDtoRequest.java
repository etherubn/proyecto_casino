package com.atlantic.proyect.dto.request.update;

import com.atlantic.proyect.dto.request.create.TipoJuegoDtoRequest;
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
public class JuegoUpdateDtoRequest {
    @JsonProperty("id")
    private Long idJuego;
    @Size(min =3,message = "Debe tener más de 2 letras")
    private String nombre;
    private TipoJuegoDtoRequest tipoJuego;
}
