package com.caito.gestionrestaurante.controller;

import com.caito.gestionrestaurante.dto.JwtDTO;
import com.caito.gestionrestaurante.dto.LogginDTO;
import com.caito.gestionrestaurante.dto.UsuarioDTO;
import com.caito.gestionrestaurante.dto.UsuarioNuevoDTO;
import com.caito.gestionrestaurante.service.contrac.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurantes/auth")
@Tag(name = "Restaurantes-Autorizacion")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/loggin")
    @Operation(description = "autenticacion de usuario", summary = "autenticacion de usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<JwtDTO> loggin(@RequestBody LogginDTO dto){
        return ResponseEntity.ok(usuarioService.login(dto));
    }

    @PostMapping("/crear-usuario")
    @Operation(description = "creacion de usuarios", summary = "creacion de usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UsuarioDTO> crearUsuario(@RequestBody UsuarioNuevoDTO dto){
        return new ResponseEntity<>(usuarioService.createUsuario(dto), HttpStatus.CREATED);
    }

}
