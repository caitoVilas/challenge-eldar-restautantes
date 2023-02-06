package com.caito.gestionrestaurante.mapper;

import com.caito.gestionrestaurante.dto.UsuarioNuevoDTO;
import com.caito.gestionrestaurante.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioNuevoMapper {

    Usuario usuarioNuevoDTOTousurio(UsuarioNuevoDTO request);
}
