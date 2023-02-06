package com.caito.gestionrestaurante.mapper;

import com.caito.gestionrestaurante.dto.RolDTO;
import com.caito.gestionrestaurante.entity.Rol;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolMapper {

    RolDTO rolToRolDTO(Rol request);
    List<RolDTO> rolListToRolDTOList(List<Rol> request);
}
