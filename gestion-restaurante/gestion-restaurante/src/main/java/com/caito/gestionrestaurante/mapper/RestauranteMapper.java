package com.caito.gestionrestaurante.mapper;

import com.caito.gestionrestaurante.dto.RestauranteDTO;
import com.caito.gestionrestaurante.entity.Restaurante;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    RestauranteDTO restauranteToRestauranteDTO(Restaurante request);
    List<RestauranteDTO> restauranteListToRestauranteDTOList(List<Restaurante> request);
}
