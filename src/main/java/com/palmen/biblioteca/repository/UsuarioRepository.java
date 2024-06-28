package com.palmen.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.palmen.biblioteca.models.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

	@Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.password = :password")
	Optional<Usuario> findByEmailAndContraseña(@Param("email") String email, @Param("password") String password);

}
