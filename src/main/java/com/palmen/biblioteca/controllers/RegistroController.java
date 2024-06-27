package com.palmen.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.palmen.biblioteca.models.entities.Usuario;
import com.palmen.biblioteca.service.IUsuarioService;

import jakarta.validation.Valid;

@Controller
public class RegistroController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/registro")
	public String registro(Usuario usuario) {
		return "registro";
	}

	@PostMapping("/registro")
	public String registrarUsuario(@Valid Usuario usuario, BindingResult result) {
		if (result.hasErrors()) {
			return "registro";
		}
		usuarioService.save(usuario);
		return "redirect:login";
	}
}
