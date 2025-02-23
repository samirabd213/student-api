package com.samir.studentapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Entity
public class Groupe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nom;  // Nom du groupe
	private String type; // "TP" ou "TD"

	@ManyToMany(mappedBy = "groupes")
	@JsonManagedReference
	private List<Student> etudiants; // Liste des étudiants du groupe

	// Constructeur par défaut
	public Groupe() {
	}

	// Constructeur avec tous les arguments
	public Groupe(Long id, String nom, String type, List<Student> etudiants) {
		this.id = id;
		this.nom = nom;
		this.type = type;
		this.etudiants = etudiants;
	}

	// Getters et Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Student> getEtudiants() {
		return etudiants;
	}

	public void setEtudiants(List<Student> etudiants) {
		this.etudiants = etudiants;
	}
}
