package com.palmen.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.palmen.biblioteca.models.entities.Libro;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	@GetMapping("/verPanelAdmin")
	public String verPanelAdmin(Libro libro) {
		return "panel-admin";
	}

	@GetMapping("/miPerfil")
	public String verMiPerfil(HttpSession session, Model model) {
		model.addAttribute("usuario", session.getAttribute("usuario"));
		return "mi-perfil";
	}

	@GetMapping("/misPrestamos")
	public String verMisPrestamos() {
		return "prestamos";
	}

}
