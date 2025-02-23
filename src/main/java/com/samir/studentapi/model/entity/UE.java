package com.samir.studentapi.model.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@Entity
public class UE {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;
	private int capacite;
	private boolean estObligatoire;

	@ManyToMany(mappedBy = "ues")
	@JsonManagedReference
	private List<Student> students;

	// Getters
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public int getCapacite() {
		return capacite;
	}

	public boolean isEstObligatoire() {
		return estObligatoire;
	}

	public List<Student> getStudents() {
		return students;
	}

	// Setters
	public void setId(Long id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setCapacite(int capacite) {
		this.capacite = capacite;
	}

	public void setEstObligatoire(boolean estObligatoire) {
		this.estObligatoire = estObligatoire;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}
