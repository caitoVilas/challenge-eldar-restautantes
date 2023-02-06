package com.caito.gestionrestaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoriaDTO {

    private long id;
    private String nombre;
    private String descripcion;
    private LocalDateTime created;
    private LocalDateTime updated;
}
