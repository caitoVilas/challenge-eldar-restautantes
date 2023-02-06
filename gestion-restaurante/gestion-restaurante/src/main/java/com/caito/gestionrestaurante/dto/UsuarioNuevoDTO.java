package com.caito.gestionrestaurante.dto;

import com.caito.gestionrestaurante.enums.RoleName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un usuario para su creacion")
public class UsuarioNuevoDTO {

    @Schema(name = "username", required = true, example = "caito")
    private String username;
    @Schema(name = "email", required = true, example = "claudio.vilas@eldars.com.ar")
    private String email;
    @Schema(name = "password", required = true, example = "mipassword123")
    private String password;
    @Schema(name = "roles")
    private List<RoleName> roles;
}
