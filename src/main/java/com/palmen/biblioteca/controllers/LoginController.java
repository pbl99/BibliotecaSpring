package com.palmen.biblioteca.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.palmen.biblioteca.models.entities.Usuario;
import com.palmen.biblioteca.service.IUsuarioService;

@Controller
public class LoginController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/login")
	public String login(Usuario usuario) {
		return "login";
	}

	@PostMapping("/autenticarUsuario")
	public String autenticarUsuario(Usuario usuario, Model model) {
		Optional<Usuario> optionalUsuario = usuarioService.autenticarUsuario(usuario.getEmail(), usuario.getPassword());
		
		if(optionalUsuario.isPresent()) {
			model.addAttribute("usuariocorrecto", "El usuario se ha identificado correctamente");
		}else {
			model.addAttribute("usuarioincorrecto", "El email o la contraseña son incorrectos, o el usuario no existe");
		}

		return "login";
	}

}
