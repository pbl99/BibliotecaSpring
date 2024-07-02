package com.palmen.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.palmen.biblioteca.models.entities.Usuario;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	@GetMapping("/inicio")
	public String inicio(HttpSession session, Model model) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		if(usuario != null) {
			model.addAttribute("admin", usuario.isEsAdmin());
			return "index";
		}else {
			return "index";
		}
	}

}
