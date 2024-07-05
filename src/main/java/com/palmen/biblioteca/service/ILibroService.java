package com.palmen.biblioteca.service;

import java.util.List;
import java.util.Optional;

import com.palmen.biblioteca.models.entities.Libro;

public interface ILibroService {
	void save(Libro libro);

	void deleteById(String isbn);

	Optional<Libro> findById(String isbn);

	List<Libro> findAll();

	List<Libro> obtenerLibrosHabilitados();

	List<Libro> obtenerLibrosDeshabilitados();

}
