package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Persona;
import com.example.demo.repo.PersonaRepo;

@Service
public class PersonaService {
	
	@Autowired
	private PersonaRepo personaRepo;
	
	public Persona findByNombre(String nombre) {
		return this.personaRepo.findByNombre(nombre);
	}
	
	public Persona savePersona(Persona persona) {
		return this.personaRepo.save(persona);
	}

}
