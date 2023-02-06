package com.caito.gestionrestaurante.dto;

import com.caito.gestionrestaurante.enums.RoleName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Schema(description = "modelo que representa un rol para las respuestas")
public class RolDTO {

    @Schema(name = "id")
    private Long id;
    @Schema(name = "roleName")
    private RoleName roleName;
}
