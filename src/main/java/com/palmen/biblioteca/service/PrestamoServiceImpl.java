package com.palmen.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palmen.biblioteca.models.entities.Prestamo;
import com.palmen.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoServiceImpl implements IPrestamoService {

	@Autowired
	private PrestamoRepository prestamoRepository;

	@Override
	public void save(Prestamo prestamo) {
		prestamoRepository.save(prestamo);
	}

	@Override
	public List<Prestamo> findAll() {
		return prestamoRepository.findAll();
	}

}
