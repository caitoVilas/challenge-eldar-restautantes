package com.caito.gestionrestaurante.service.contrac;

import com.caito.gestionrestaurante.entity.Rol;
import com.caito.gestionrestaurante.enums.RoleName;

public interface RolService {
    Rol getByRolNombre(RoleName roleName);
}
