package com.caito.gestionrestaurante.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDTO {

    private Integer code;
    private LocalDateTime timestamp;
    private String mensaje;
    private String path;
}
