package com.palmen.biblioteca.service;

import java.util.List;

import com.palmen.biblioteca.models.entities.Prestamo;

public interface IPrestamoService {
	void save(Prestamo prestamo);

	List<Prestamo> findAll();
}
