package com.caito.gestionrestaurante.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "Modelo que representa un plato para el alta")
public class PlatoNuevoDTO {

    @Schema(name = "nombre", required = true, example = "Costilla de cerdo a la Riojana")
    private String nombre;
    @Schema(name = "descripcion")
    private String descripcion;
    @Schema(name = "precio", required = true, example = "450.00")
    private BigDecimal precio;
    @Schema(name = "restauranteId", required = true, example = "1")
    private Long restauranteId;
}
