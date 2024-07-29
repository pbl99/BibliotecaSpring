package com.palmen.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.palmen.biblioteca.models.entities.Libro;
import com.palmen.biblioteca.models.entities.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsuarioController {

	@GetMapping("/verPanelAdmin")
	public String verPanelAdmin(HttpSession session, Libro libro) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		
		if (usuario == null) {
			return "redirect:/inicio";
		} else if (usuario.isEsAdmin()) {
			return "panel-admin";
		} else {
			return "redirect:/inicio";
		}
		
	}

	@GetMapping("/miPerfil")
	public String verMiPerfil(HttpSession session, Model model) {
		model.addAttribute("usuario", session.getAttribute("usuario"));
		return "mi-perfil";
	}
}
