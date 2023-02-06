package com.caito.gestionrestaurante.service.contrac;

import com.caito.gestionrestaurante.dto.CategoriaDTO;
import com.caito.gestionrestaurante.dto.CategoriaNuevaDTO;
import com.caito.gestionrestaurante.dto.PageableResponseDTO;

import java.util.List;

public interface CategoriaService {

    CategoriaDTO createCategoria(CategoriaNuevaDTO dto);
    CategoriaDTO getCategoriaById(Long id);
    CategoriaDTO getCategoriaByNombre(String nombre);
    PageableResponseDTO<CategoriaDTO> getAll(int page, int size);
    void deletecategoria(Long id);
    List<CategoriaDTO> verTodos();
}
