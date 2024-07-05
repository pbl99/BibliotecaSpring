package com.palmen.biblioteca.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.palmen.biblioteca.models.entities.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
	@Query("SELECT p FROM Prestamo p WHERE p.libro.isbn = :isbn")
	Optional<Prestamo> findByLibroIsbn(@Param("isbn") String isbn);
}
