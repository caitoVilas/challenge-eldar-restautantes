package com.caito.gestionrestaurante.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "restaurantes")
@Data
@SQLDelete(sql = "UPDATE restaurantes SET deleted=true WHERE id=?")
@Where(clause = "deleted=false")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String descripcion;
    private String domicilio;
    private String localidad;
    private String provincia;
    private String telefono;
    private String email;
    @CreationTimestamp
    private LocalDateTime created;
    @UpdateTimestamp
    private LocalDateTime updated;
    private boolean deleted;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @OneToMany(mappedBy = "restaurante")
    private List<Plato> platos;
}
