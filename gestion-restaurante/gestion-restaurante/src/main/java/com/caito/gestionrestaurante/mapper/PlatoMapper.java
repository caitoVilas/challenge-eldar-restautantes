package com.caito.gestionrestaurante.mapper;

import com.caito.gestionrestaurante.dto.PlatoDTO;
import com.caito.gestionrestaurante.entity.Plato;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlatoMapper {

    PlatoDTO platoToPlatoDTO(Plato request);
    List<PlatoDTO> platoListToPlatoDTOList(List<Plato> request);
}
