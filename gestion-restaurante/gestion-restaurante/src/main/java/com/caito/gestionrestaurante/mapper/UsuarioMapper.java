package com.caito.gestionrestaurante.mapper;

import com.caito.gestionrestaurante.dto.UsuarioDTO;
import com.caito.gestionrestaurante.entity.Usuario;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO usuarioToUsuarioDTO(Usuario request);
    List<UsuarioDTO> usuarioListToUsuarioDTOList(List<Usuario> request);
}
