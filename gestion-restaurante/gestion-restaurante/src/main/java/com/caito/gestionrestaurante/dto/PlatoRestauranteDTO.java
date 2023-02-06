package com.caito.gestionrestaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlatoRestauranteDTO {

    private RestauranteDTO restaurante;
    private List<PlatoSoloDTO> platos;
    private int page;
    private Long results;
    private int totalPages;
}
