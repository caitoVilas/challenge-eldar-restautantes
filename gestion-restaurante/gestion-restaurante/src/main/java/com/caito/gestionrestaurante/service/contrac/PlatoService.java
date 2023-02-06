package com.caito.gestionrestaurante.service.contrac;

import com.caito.gestionrestaurante.dto.PageableResponseDTO;
import com.caito.gestionrestaurante.dto.PlatoDTO;
import com.caito.gestionrestaurante.dto.PlatoNuevoDTO;
import com.caito.gestionrestaurante.dto.PlatoRestauranteDTO;

import java.util.List;

public interface PlatoService {

    PlatoDTO createPlato(PlatoNuevoDTO dto);
    List<PlatoDTO> verTodos();
    PageableResponseDTO vertodosPaginado(int page, int size);
    PlatoDTO getById(Long id);
    List<PlatoDTO> getByNombre(String nombre);
    void deletePlato(Long id);
    PlatoRestauranteDTO buscarporRestaurante(Long idRestaurante, int page, int size);


}
