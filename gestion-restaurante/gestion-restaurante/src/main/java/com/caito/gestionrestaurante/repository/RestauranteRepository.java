package com.caito.gestionrestaurante.repository;

import com.caito.gestionrestaurante.entity.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestauranteRepository extends JpaRepository<Restaurante, Long> {

    Restaurante findByNombreContaining(String nombre);
    boolean existsByNombre(String nombre);
}
