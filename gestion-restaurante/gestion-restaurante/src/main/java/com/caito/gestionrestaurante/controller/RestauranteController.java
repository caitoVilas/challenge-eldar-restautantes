package com.caito.gestionrestaurante.controller;

import com.caito.gestionrestaurante.dto.PageableResponseDTO;
import com.caito.gestionrestaurante.dto.RestauranteDTO;
import com.caito.gestionrestaurante.dto.RestauranteNuevoDTO;
import com.caito.gestionrestaurante.service.contrac.RestauranteService;
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
@RequestMapping("/api/v1/restaurantes")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Restaurantes-Restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    @Operation(description = "creacion de restaurantes", summary = "creacion de restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<RestauranteDTO> createRestaurante(@RequestBody RestauranteNuevoDTO dto){
        return ResponseEntity.ok(restauranteService.createRestaurante(dto));
    }

    @GetMapping
    @Operation(description = "consulta todos los restaurantes", summary = "consulta todos los restaurantes")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<RestauranteDTO>> getAll(){
        List<RestauranteDTO> restaurantes = restauranteService.getAll();
        if (restaurantes.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping("/{id}")
    @Operation(description = "buscar un restaurante por id si existe", summary = "buscar un restaurante por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<RestauranteDTO> getById(@PathVariable Long id){

        return ResponseEntity.ok(restauranteService.getById(id));
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(description = "buscar un restaurante por nombre si existe", summary = "buscar un restaurante por nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<RestauranteDTO> getByNombre(@PathVariable String nombre){

        return ResponseEntity.ok(restauranteService.getByNombre(nombre));
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todos los restaurantes paginado", summary = "consulta todos los restaurantes paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO> getAllPaginado(@RequestParam(defaultValue = "1") int page,
                                                              @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO<RestauranteDTO> restaurantes = restauranteService.verTodosPaginado(page, size);
        if (restaurantes.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(restaurantes);
    }

    @DeleteMapping("/{id}")
    @Operation(description = "eliminar un restaurente por id si existe", summary = "eliminar un restaurante por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> deleteResytaurante(@PathVariable Long id){

        restauranteService.deleteResturante(id);
        return ResponseEntity.ok(null);
    }
}
