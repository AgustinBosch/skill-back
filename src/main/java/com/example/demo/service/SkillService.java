package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Skill;
import com.example.demo.repo.SkillRepo;

@Service
public class SkillService {

	@Autowired
	private SkillRepo skillRepo;
	
	public Skill findById(int id) {
		return this.skillRepo.findById(id).orElse(null);
	}
	
	public Skill saveSkill(Skill skill) {
		return this.skillRepo.save(skill);
	}
	
	public boolean deleteSkill(int id) {
		if (this.skillRepo.existsById(id)){
			this.skillRepo.deleteById(id);
			return true;
		}
		return false;
	}
	
}
