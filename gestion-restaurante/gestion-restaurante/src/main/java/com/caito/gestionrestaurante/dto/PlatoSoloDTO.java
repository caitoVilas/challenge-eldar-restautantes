package com.caito.gestionrestaurante.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatoSoloDTO {

    @Schema(name = "id")
    private Long id;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
}
