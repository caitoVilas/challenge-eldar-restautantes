package com.caito.gestionrestaurante.controller;

import com.caito.gestionrestaurante.dto.PageableResponseDTO;
import com.caito.gestionrestaurante.dto.UsuarioDTO;
import com.caito.gestionrestaurante.service.contrac.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/restaurantes/usuarios")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Restaurantes-Usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    @Operation(description = "consulta de usuarios por id si existe", summary = "consulta de usuarios por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<UsuarioDTO> getUsuarioById(@PathVariable Long id){
        return ResponseEntity.ok(usuarioService.getUsuarioById(id));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "eliminar un usuario por id si existe", summary = "eliminar un usuario por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> eliminarUsuario(@PathVariable Long id){
        usuarioService.deleteUsuario(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping
    @Operation(description = "consulta todos los usuarios", summary = "consulta todos los usuarios")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO> verTodos(@RequestParam(defaultValue = "1") int page,
                                                        @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO usuarios = usuarioService.verTodos(page, size);
        if (usuarios.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(usuarios);
    }

    @PatchMapping("/cambiar-rol/{usuarioId}")
    @Operation(description = "cabiar rol a usuario", summary = "camiar rol a usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> cambiarRol(@PathVariable Long usuarioId){
        usuarioService.cambiarRol(usuarioId);
        return ResponseEntity.ok(null);
    }
}
