package com.caito.gestionrestaurante.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un usuario para las respuestas")
public class UsuarioDTO {

    @Schema(name = "id")
    private Long id;
    @Schema(name = "username")
    private String username;
    @Schema(name = "email")
    private String email;
    @Schema(name = "created")
    private LocalDateTime created;
    @Schema(name = "updated")
    private LocalDateTime updated;
    @Schema(name = "roles")
    private List<RolDTO> roles;
}
