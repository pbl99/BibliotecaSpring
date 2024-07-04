package com.palmen.biblioteca.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.palmen.biblioteca.models.dto.LoginDTO;
import com.palmen.biblioteca.models.entities.Usuario;
import com.palmen.biblioteca.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class LoginController {

	@Autowired
	private IUsuarioService usuarioService;

	@GetMapping("/login")
	public String login(LoginDTO loginDTO) {
		return "login";
	}

	@PostMapping("/autenticarUsuario")
	public String autenticarUsuario(@Valid LoginDTO loginDTO, BindingResult result, Model model, HttpSession session) {
		
		if (result.hasErrors()) {
			return "login";
		}

		Optional<Usuario> optionalUsuario = usuarioService.autenticarUsuario(loginDTO.getEmail(), loginDTO.getPassword());

		if (optionalUsuario.isPresent()) {
			Usuario usuarioAutenticado = optionalUsuario.get();
			session.setAttribute("usuario", usuarioAutenticado);
			model.addAttribute("admin", usuarioAutenticado.isEsAdmin());
			return "redirect:/inicio";
		} else {
			model.addAttribute("usuarioincorrecto", "El email o la contraseña son incorrectos, o el usuario no existe");
			return "login";
		}

	}

	@GetMapping("/cerrarSesion")
	public String cerrarSesion(HttpSession session) {
		session.invalidate(); // Invalida la sesión actual
		return "redirect:/inicio"; // Redirige a la página principal (o donde desees)
	}

}
