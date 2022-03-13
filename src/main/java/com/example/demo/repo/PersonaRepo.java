package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Persona;

public interface PersonaRepo extends JpaRepository<Persona, Integer> {
	Persona findByNombre(String nombre);
}
