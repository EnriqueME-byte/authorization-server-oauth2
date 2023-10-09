package com.sakicorp.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "tblroles")
public class Rol {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_rol")
    private Long id;

    @Column(name = "nombre", unique = true, length = 12)
    private String nombre;
}
