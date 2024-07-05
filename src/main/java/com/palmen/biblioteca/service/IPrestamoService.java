package com.palmen.biblioteca.service;

import java.util.List;
import java.util.Optional;

import com.palmen.biblioteca.models.entities.Prestamo;

public interface IPrestamoService {
	void save(Prestamo prestamo);

	Optional<Prestamo> findByLibroIsbn(String isbn);

	List<Prestamo> findAll();
}
