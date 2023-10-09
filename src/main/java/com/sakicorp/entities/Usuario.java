package com.sakicorp.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "tblusuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "enabled", length = 1)
    private Character enabled;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tblusuarios_roles", joinColumns = @JoinColumn(name = "id_usuario") ,
    inverseJoinColumns = @JoinColumn(name = "id_rol"),
    uniqueConstraints = {@UniqueConstraint(columnNames = {"id_usuario", "id_rol"})})
    private List<Rol> roles;
}
