package com.caito.gestionrestaurante.mapper;

import com.caito.gestionrestaurante.dto.RestauranteNuevoDTO;
import com.caito.gestionrestaurante.entity.Restaurante;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestauranteNuevoMapper {

    Restaurante restauranteNuevoDTOToRestaurante(RestauranteNuevoDTO request);
}
