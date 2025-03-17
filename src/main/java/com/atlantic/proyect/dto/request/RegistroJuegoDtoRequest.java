package com.atlantic.proyect.dto.request;

import com.atlantic.proyect.entity.Juego;
import com.atlantic.proyect.entity.Local;
import com.atlantic.proyect.entity.Tarjeta;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RegistroJuegoDtoRequest {
    @JsonProperty("id")
    private Long idRegistroJuego;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime fechaRegistroJuego;
    @Min(message = "La ganancia", value = 0)
    @NotNull(message = "La ganancia debe tener contenido")
    private Double ganancia;
    @Min(message = "La ganancia", value = 0)
    @NotNull(message = "La perdida debe tener contenido")
    private Double perdida;
    @NotNull(message = "La tarjeta debe tener contenido")
    private TarjetaDtoRequest tarjeta;
    @NotNull(message = "El local debe tener contenido")
    private Local local;
    @NotNull(message = "El juego debe tener contenido")
    private Juego juego;
}
