package com.caito.gestionrestaurante.controller;

import com.caito.gestionrestaurante.dto.PageableResponseDTO;
import com.caito.gestionrestaurante.dto.PlatoDTO;
import com.caito.gestionrestaurante.dto.PlatoNuevoDTO;
import com.caito.gestionrestaurante.dto.PlatoRestauranteDTO;
import com.caito.gestionrestaurante.service.contrac.PlatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurante/platos")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Resteurantes-Platos")
public class PlatoController {

    @Autowired
    private PlatoService platoService;

    @PostMapping
    @Operation(description = "creacion de platos", summary = "creacion de platos")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PlatoDTO> createPlato(@RequestBody PlatoNuevoDTO dto){

        return ResponseEntity.ok(platoService.createPlato(dto));
    }

    @GetMapping
    @Operation(description = "consulta todos los platos", summary = "consulta todos los platos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<PlatoDTO>> verTodos(){

        List<PlatoDTO> platos = platoService.verTodos();
        if (platos.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(platos);
    }

    @PostMapping("/paginado")
    @Operation(description = "consulta todos los platos paginado", summary = "consulta todos los platos paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO> vertodosPaginado(@RequestParam(defaultValue = "1") int page,
                                                                @RequestParam(defaultValue = "10") int size){

        PageableResponseDTO response = platoService.vertodosPaginado(page, size);
        if (response.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @Operation(description = "buscar un plato por id si existe", summary = "buscar un plato por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PlatoDTO> getByid(@PathVariable Long id){

        return ResponseEntity.ok(platoService.getById(id));
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(description = "buscar un plato por nombre si existe", summary = "buscar un plato por nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<PlatoDTO>> finByNombre(@PathVariable String nombre){

        return ResponseEntity.ok(platoService.getByNombre(nombre));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "eliminar un plato por id si existe", summary = "eliminar u plato por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> deletePlato(@PathVariable Long id){

        platoService.deletePlato(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/xrestaurante/{idRestaurante}")
    @Operation(description = "consulta todos los platos por restaurante paginado", summary = "consulta todos los platos por restaurante paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PlatoRestauranteDTO> verPlatosXRestaurant(@RequestParam(defaultValue = "1") int page,
                                                                    @RequestParam(defaultValue = "10") int size,
                                                                    @PathVariable Long idRestaurante){
        PlatoRestauranteDTO response = platoService.buscarporRestaurante(idRestaurante, page, size);
        if (response.getPlatos().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(response);
    }
}
