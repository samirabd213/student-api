package com.samir.studentapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
public class Formation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@OneToMany(mappedBy = "formation")
	@JsonManagedReference
	private List<Student> etudiants; // Liste des étudiants inscrits à cette formation

	// Getters and setters

	// Constructeurs, getters et setters
	public Formation() {}

	public Formation(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	// Getter pour la liste des étudiants
	public List<Student> getEtudiants() {
		return etudiants;
	}

	// Setter pour la liste des étudiants
	public void setEtudiants(List<Student> etudiants) {
		this.etudiants = etudiants;
	}
}
