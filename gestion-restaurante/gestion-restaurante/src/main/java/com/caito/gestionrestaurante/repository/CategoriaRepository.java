package com.caito.gestionrestaurante.repository;

import com.caito.gestionrestaurante.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria findByNombreContaining(String nombre);
    boolean existsByNombre(String nombre);
}
