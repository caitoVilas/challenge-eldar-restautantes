package com.caito.gestionrestaurante.mapper;

import com.caito.gestionrestaurante.dto.CategoriaDTO;
import com.caito.gestionrestaurante.entity.Categoria;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO categoriaToCategoriaDTO(Categoria request);
    List<CategoriaDTO> categoriaListToCategoriaListDTO(List<Categoria> request);
}
