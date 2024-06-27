package com.palmen.biblioteca.service;

import java.util.List;
import java.util.Optional;

import com.palmen.biblioteca.models.entities.Libro;

public interface ILibroService {
	void save(Libro libro);

	void deleteById(Integer isbn);

	Optional<Libro> findById(Integer isbn);

	List<Libro> findAll();

}
