package com.caito.gestionrestaurante.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "categorias")
@Data
@SQLDelete(sql = "UPDATE categorias SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;
    private boolean deleted;
    @OneToMany(mappedBy = "categoria")
    private List<Restaurante> restaurantes;

}
