package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.model.Skill;
import com.example.demo.model.SkillDTO;
import com.example.demo.service.PersonaService;
import com.example.demo.service.SkillService;

@RestController
@CrossOrigin("/*")
public class SkillController {
	
	//destinatario de la skill hardcodeado
	//soluciones para saber a quier guardarle la skill
	//mandar nombre en SkillDTO  (opción rapida)
	//leer usuario desde el JWT (opción segura)
	private final String NOMBRE="pepito";
	
	@Autowired
	private SkillService skillService;
	@Autowired
	private PersonaService personaService;

	@GetMapping("/skill/{id}")
	public ResponseEntity<Skill> getSkill(@PathVariable int id){
		Skill s = this.skillService.findById(id);
		return ResponseEntity.ok(s);
	}
	
	
	@PostMapping("/skill/nueva")
	public ResponseEntity<Skill> newSkill(@RequestBody SkillDTO skill){
		Persona p = personaService.findByNombre(this.NOMBRE);
		Skill s = new Skill();
		s.setNivel(skill.getNivel());
		s.setNombre(skill.getNombre());
		s.setPersona(p);
		Skill newSkill = skillService.saveSkill(s);
		return ResponseEntity.status(HttpStatus.CREATED).body(newSkill);
	}
	
	@PutMapping("/skill/editar")
	public ResponseEntity<Skill> editSkill(@RequestBody SkillDTO skill){
		Skill s = this.skillService.findById(skill.getId());
		Persona p = personaService.findByNombre(this.NOMBRE);
		s.setNombre(skill.getNombre());
		s.setNivel(skill.getNivel());
		s.setPersona(p);
		Skill newSkill = this.skillService.saveSkill(s);
		return ResponseEntity.status(HttpStatus.CREATED).body(newSkill);
	}
	
	@DeleteMapping("/skill/borrar/{id}")
	public ResponseEntity<?> deleteSkill(@PathVariable int id){
		this.skillService.deleteSkill(id);
		return ResponseEntity.noContent().build();
	}
}
