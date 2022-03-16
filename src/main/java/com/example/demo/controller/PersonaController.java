package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.service.PersonaService;

@RestController
@CrossOrigin("/*")
public class PersonaController {
	
	@Autowired
	private PersonaService personaService;
	
	// TODO manejar errores
	@GetMapping("/persona/{nombre}")
	public ResponseEntity<Persona> getPersona(@PathVariable String nombre) {
		Persona p = this.personaService.findByNombre(nombre);
		return ResponseEntity.ok(p);
	}
	
	// TODO manejar errores
	@PostMapping("/persona/nueva")
	public ResponseEntity<Persona> newPersona(@RequestBody Persona persona) {
		Persona p = this.personaService.savePersona(persona);
		return ResponseEntity.ok(p);
	}
}
