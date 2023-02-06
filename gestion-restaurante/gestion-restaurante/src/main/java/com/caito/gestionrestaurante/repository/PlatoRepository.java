package com.caito.gestionrestaurante.repository;

import com.caito.gestionrestaurante.entity.Plato;
import com.caito.gestionrestaurante.entity.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatoRepository extends JpaRepository<Plato, Long> {

    List<Plato> findByNombreContaining(String nombre);
    Page<Plato> findByRestaurante(Restaurante restaurante, Pageable pageable);
}
