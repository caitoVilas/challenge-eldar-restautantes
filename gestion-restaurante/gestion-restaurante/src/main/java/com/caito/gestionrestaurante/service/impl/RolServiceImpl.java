package com.caito.gestionrestaurante.service.impl;

import com.caito.gestionrestaurante.entity.Rol;
import com.caito.gestionrestaurante.enums.RoleName;
import com.caito.gestionrestaurante.repository.RolRepository;
import com.caito.gestionrestaurante.service.contrac.RolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    private static final Logger logger = LoggerFactory.getLogger(RolServiceImpl.class);


    @Override
    public Rol getByRolNombre(RoleName roleName) {
        logger.info("inicio servicio buscar rol por nombre de rol");
        return rolRepository.findByRoleName(roleName);
    }
}
