package com.caito.gestionrestaurante.controller;

import com.caito.gestionrestaurante.dto.CategoriaDTO;
import com.caito.gestionrestaurante.dto.CategoriaNuevaDTO;
import com.caito.gestionrestaurante.dto.PageableResponseDTO;
import com.caito.gestionrestaurante.service.contrac.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/restaurantes/categoria")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Restaurantes-Categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    @Operation(description = "creacion de categorias", summary = "creacion de categorias")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "created"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<CategoriaDTO> crearCategoria(@RequestBody CategoriaNuevaDTO dto){
        return new ResponseEntity<>(categoriaService.createCategoria(dto), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(description = "consulta todas las categoria", summary = "consulta todas las categorias")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<List<CategoriaDTO>> verTodos(){
        List<CategoriaDTO> categorias = categoriaService.verTodos();
        if (categorias.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping("/{id}")
    @Operation(description = "buscar una categoria por id si existe", summary = "buscar una categoria por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<CategoriaDTO> finById(@PathVariable Long id){

        return ResponseEntity.ok(categoriaService.getCategoriaById(id));
    }

    @GetMapping("/nombre/{nombre}")
    @Operation(description = "buscar una categoria por nombre si existe", summary = "buscar una categoria por nombre")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<CategoriaDTO> findByNombre(@PathVariable String nombre){

        return ResponseEntity.ok(categoriaService.getCategoriaByNombre(nombre));
    }

    @DeleteMapping("/{id}")
    @Operation(description = "eliminar una categoria por id si existe", summary = "eliminar una categoria por id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "404", description = "not found"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<?> deleteCategoria(@PathVariable Long id){
        categoriaService.deletecategoria(id);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/paginado")
    @Operation(description = "consulta todas las categoria paginado", summary = "consulta todas las categorias paginado")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "ok"),
            @ApiResponse(responseCode = "204", description = "no content"),
            @ApiResponse(responseCode = "401", description = "unauthorized"),
            @ApiResponse(responseCode = "500", description = "server error")
    })
    public ResponseEntity<PageableResponseDTO> verCategoriasPaginado(@RequestParam(defaultValue = "1") int page,
                                                                     @RequestParam(defaultValue = "10") int size){
        PageableResponseDTO categorias = categoriaService.getAll(page, size);
        if (categorias.getContent().isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(categorias);
    }
}
