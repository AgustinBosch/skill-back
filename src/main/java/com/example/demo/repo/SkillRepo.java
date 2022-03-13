package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Skill;

public interface SkillRepo extends JpaRepository<Skill, Integer> {

}
