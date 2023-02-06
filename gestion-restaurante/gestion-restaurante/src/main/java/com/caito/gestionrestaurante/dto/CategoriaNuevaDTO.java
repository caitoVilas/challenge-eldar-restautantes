package com.caito.gestionrestaurante.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa una categoria para su creacion")
public class CategoriaNuevaDTO {

    private String nombre;
    private String descripcion;

}
