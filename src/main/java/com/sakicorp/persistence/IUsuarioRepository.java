package com.sakicorp.persistence;

import com.sakicorp.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findOneByEmail(String email);

}
