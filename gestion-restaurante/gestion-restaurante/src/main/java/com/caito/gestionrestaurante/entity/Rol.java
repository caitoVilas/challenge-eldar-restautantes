package com.caito.gestionrestaurante.entity;

import com.caito.gestionrestaurante.enums.RoleName;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private RoleName roleName;
}
