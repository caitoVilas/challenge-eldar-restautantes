package com.caito.gestionrestaurante.service.contrac;

import com.caito.gestionrestaurante.dto.PageableResponseDTO;
import com.caito.gestionrestaurante.dto.RestauranteDTO;
import com.caito.gestionrestaurante.dto.RestauranteNuevoDTO;

import java.util.List;

public interface RestauranteService {

    RestauranteDTO createRestaurante(RestauranteNuevoDTO dto);
    List<RestauranteDTO> getAll();
    RestauranteDTO getById(Long id);
    RestauranteDTO getByNombre(String nombre);
    PageableResponseDTO verTodosPaginado(int page, int size);
    void deleteResturante(Long id);
}
