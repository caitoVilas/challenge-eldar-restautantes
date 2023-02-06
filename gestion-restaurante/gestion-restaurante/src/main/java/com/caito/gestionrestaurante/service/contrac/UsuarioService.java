package com.caito.gestionrestaurante.service.contrac;

import com.caito.gestionrestaurante.dto.*;

public interface UsuarioService {

    UsuarioDTO createUsuario(UsuarioNuevoDTO dto);
    UsuarioDTO getUsuarioById(Long id);
    void deleteUsuario(Long id);
    JwtDTO login(LogginDTO dto);
    PageableResponseDTO verTodos(int page, int size);
}
