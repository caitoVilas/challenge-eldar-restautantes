package com.caito.gestionrestaurante.mapper;

import com.caito.gestionrestaurante.dto.CategoriaNuevaDTO;
import com.caito.gestionrestaurante.entity.Categoria;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoriaNuevaMapper {

    Categoria categoriaNuevaDTOToCategoria(CategoriaNuevaDTO request);
}
