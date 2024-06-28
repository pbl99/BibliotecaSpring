package com.palmen.biblioteca.service;

import java.util.Optional;

import com.palmen.biblioteca.models.entities.Usuario;

public interface IUsuarioService {
	void save(Usuario usuario);

	Optional<Usuario> autenticarUsuario(String email, String password);

}
