package com.palmen.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.palmen.biblioteca.models.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
