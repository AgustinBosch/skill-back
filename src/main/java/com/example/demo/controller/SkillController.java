package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Persona;
import com.example.demo.model.Skill;
import com.example.demo.service.SkillService;

@RestController
@CrossOrigin("/*")
@RequestMapping("/skill")
public class SkillController {
	
	private final int PERSONA_ID=1; // fk hardcodeada, idealmente sacarla del JWT
	
	@Autowired
	private SkillService skillService;

	@GetMapping("/{id}")
	public ResponseEntity<Skill> getSkill(@PathVariable int id){
		Skill s = this.skillService.findById(id);
		if(s != null) {
			return ResponseEntity.ok(s);			
		}
		return ResponseEntity.notFound().build();
	}
	
	
	@PostMapping("/nueva")
	public ResponseEntity<Skill> newSkill(@Valid @RequestBody Skill skill, Errors errors){
		
		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Persona ps = new Persona();
		ps.setId(PERSONA_ID); // fk hardcodeada, idealmente sacarla del JWT
		
		skill.setPersona(ps);
		Skill newSkill = skillService.saveSkill(skill);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(newSkill);
	}
	
	@PutMapping("/editar")
	public ResponseEntity<Skill> editSkill(@Valid @RequestBody Skill skill, Errors errors){
		
		if (errors.hasErrors()) // devolver mensaje de los errores en lugar de solo un 400
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		
		Skill s = this.skillService.findById(skill.getId());
		
		if(s == null)
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		
		s.setNombre(skill.getNombre());
		s.setNivel(skill.getNivel());
		Skill newSkill = this.skillService.saveSkill(s);
		
		return ResponseEntity.status(HttpStatus.OK).body(newSkill);
	}
	
	@DeleteMapping("/borrar/{id}")
	public ResponseEntity<Void> deleteSkill(@PathVariable int id){
		if(this.skillService.deleteSkill(id)) {		
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}
}
