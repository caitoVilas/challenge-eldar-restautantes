package com.caito.gestionrestaurante.repository;

import com.caito.gestionrestaurante.entity.Rol;
import com.caito.gestionrestaurante.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {
    Rol findByRoleName(RoleName roleName);
}
