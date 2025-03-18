package com.atlantic.proyect.dto.request.create;

import com.atlantic.proyect.entity.Juego;
import com.atlantic.proyect.entity.Local;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double ganancia=0d;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double perdida=0d;
    @NotNull(message = "La tarjeta debe tener contenido")
    private TarjetaDtoRequest tarjeta;
    @NotNull(message = "El local debe tener contenido")
    private LocalDtoRequest local;
    @NotNull(message = "El juego debe tener contenido")
    private JuegoDtoRequest juego;
    @NotNull(message = "Debe tener un monto de apuesta")
    @Positive(message = "La apuesta debe ser positiva")
    @Min(value = 1,message = "LA apuesta mínima es de 1 sol")
    private Double montoApuesta;
}
