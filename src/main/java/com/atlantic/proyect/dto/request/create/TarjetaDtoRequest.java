package com.atlantic.proyect.dto.request.create;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TarjetaDtoRequest {
    @JsonProperty("id")
    private Long idTarjeta;
    @NotNull(message = "El jugador debe tener contenido")
    private JugadorDtoRequest jugador;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String codigo;
    @Min(value = 0,message = "El monto debe ser mayor a 0")
    private Double monto=0.0;
}
