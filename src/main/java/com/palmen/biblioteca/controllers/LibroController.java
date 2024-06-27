package com.palmen.biblioteca.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.palmen.biblioteca.models.entities.Libro;
import com.palmen.biblioteca.service.ILibroService;

@Controller
public class LibroController {

	@Autowired
	private ILibroService libroService;

	@PostMapping("/crearLibros")
	public String crearLibros(Libro libro) {
		libroService.save(libro);

		return "libros";
	}

	@PostMapping("/borrarLibros")
	public String borrarLibros(Libro libro) {
		libroService.deleteById(libro.getIsbn());
		return "libros";
	}

	@PostMapping("/buscarPorId")
	public String buscarPorId(Libro libro, Model model) {
		Optional<Libro> optionalLibro = libroService.findById(libro.getIsbn());
		if (optionalLibro.isPresent()) {
			model.addAttribute("libro", optionalLibro.get());
		} else {
			// Manejar el caso cuando no se encuentra el libro, por ejemplo, agregando un
			// mensaje de error
			model.addAttribute("error", "Libro no encontrado");
		}
		return "ver-libros";
	}

	@GetMapping("/buscarTodos")
	public String buscarTodos(Model model) {
		model.addAttribute("libro", libroService.findAll());
		return "ver-libros";
	}

}
