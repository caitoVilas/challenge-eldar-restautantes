package com.caito.gestionrestaurante.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un restaurante para su creacion")
public class RestauranteNuevoDTO {

    @Schema(name = "nombre", required = true, example = "Don Antonio")
    private String nombre;
    @Schema(name = "descripcion", required = false)
    private String descripcion;
    @Schema(name = "domicilio", required = true, example = "Ecuador 700")
    private String domicilio;
    @Schema(name = "localidad", required = true, example = "Lanus")
    private String localidad;
    @Schema(name = "provincia", required = true, example = "Buenos Aires")
    private String provincia;
    @Schema(name = "telefono", required = true, example = "1167281038")
    private String telefono;
    @Schema(name = "email", required = true, example = "antonio@mail.com")
    private String email;
}
