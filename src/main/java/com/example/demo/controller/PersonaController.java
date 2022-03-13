package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;

@RestController
@CrossOrigin("/*")
public class PersonaController {
	
	@Autowired
	private PersonaService personaService;
	
	@GetMapping("/persona/{nombre}")
	public ResponseEntity<Persona> getPersona(@PathVariable String nombre) {
		Persona p = this.personaService.findByNombre(nombre);
		return ResponseEntity.ok(p);
	}
}
