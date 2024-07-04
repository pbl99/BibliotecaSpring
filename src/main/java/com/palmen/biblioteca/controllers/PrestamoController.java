package com.palmen.biblioteca.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.palmen.biblioteca.models.entities.Libro;
import com.palmen.biblioteca.models.entities.Prestamo;
import com.palmen.biblioteca.models.entities.Usuario;
import com.palmen.biblioteca.service.ILibroService;
import com.palmen.biblioteca.service.IPrestamoService;
import com.palmen.biblioteca.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PrestamoController {

	@Autowired
	private IPrestamoService prestamoService;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private ILibroService libroService;

	// Este método al final no deja de ser un save de préstamo
	@PostMapping("/reservarLibro")
	public String reservarLibro(Libro libro, HttpSession session, Model model) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");

		if (usuario == null) {
			return "redirect:/login";
		}

		Optional<Libro> optionalLibro = libroService.findById(libro.getIsbn());

		if (!optionalLibro.isPresent()) {
			model.addAttribute("error", "El libro no fue encontrado");
			return "redirect:/catalogo";
		}
		
		Libro libroActualizado = optionalLibro.get();
		libroActualizado.setEstaReservado(true);
		libroService.save(libroActualizado);

		Prestamo nuevoPrestamo = new Prestamo();
		nuevoPrestamo.setLibro(libroActualizado);
		nuevoPrestamo.setUsuario(usuario);
		nuevoPrestamo.setFechaPrestamo(LocalDate.now());
		nuevoPrestamo.setFechaDevolucion(LocalDate.now().plus(15, ChronoUnit.DAYS));
		nuevoPrestamo.setDevuelto(false);

		prestamoService.save(nuevoPrestamo);

		return "redirect:/misPrestamos";
	}

	@GetMapping("/misPrestamos")
	public String verPrestamos(HttpSession session, Model model) {
		Usuario usuario = (Usuario) session.getAttribute("usuario");
		List<Prestamo> prestamos = prestamoService.findAll();
		
		List<Prestamo> prestamosUsuario = new ArrayList<>();
		if (usuario != null) {
			for (Prestamo prestamo : prestamos) {
				if (prestamo.getUsuario().getEmail().equals(usuario.getEmail())) {
					prestamosUsuario.add(prestamo);
				}
			}
			model.addAttribute("prestamos", prestamosUsuario);
		} else {
			model.addAttribute("error", "Necesitas iniciar sesión para ver tu historial de préstamos"); // O una lista vacía si prefieres
		}
		return "prestamos";
	}

}
