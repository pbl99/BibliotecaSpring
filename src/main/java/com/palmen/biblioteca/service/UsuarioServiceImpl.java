package com.palmen.biblioteca.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmen.biblioteca.models.entities.Usuario;
import com.palmen.biblioteca.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	//Identificador para el administrador
	private static final String ADMIN_USERNAME = "Admin";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public void save(Usuario usuario) {
		boolean isAdmin = ADMIN_USERNAME.equals(usuario.getUsername());
		usuario.setEsAdmin(isAdmin);
		usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> autenticarUsuario(String email, String password) {
		return usuarioRepository.findByEmailAndContraseña(email, password);
	}

}
