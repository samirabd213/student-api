package com.samir.studentapi.controller;

import com.samir.studentapi.model.dto.StudentDTO;
import com.samir.studentapi.model.entity.Formation;
import com.samir.studentapi.model.entity.Groupe;
import com.samir.studentapi.model.entity.Student;
import com.samir.studentapi.model.entity.UE;
import com.samir.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/etudiants")
@CrossOrigin(origins = "*")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@GetMapping("/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
		Optional<Student> student = studentService.getStudentById(id);
		return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PostMapping("/create")
	public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDTO) {
		Student createdStudent = studentService.createStudent(studentDTO);
		return ResponseEntity.ok(createdStudent);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody StudentDTO studentDTO) {
		Optional<Student> updatedStudent = studentService.updateStudent(id, studentDTO);
		return updatedStudent.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
		boolean deleted = studentService.deleteStudent(id);
		return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
	}
/*
	@GetMapping("/{id}/formations")
	public ResponseEntity<Formation> getStudentFormation(@PathVariable Long id) {
		Optional<Formation> formation = studentService.getStudentFormation(id);
		return formation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Nouvelle méthode pour inscrire un étudiant à une formation
	@GetMapping("/{id}/inscription-formations/{id_form}")
	public ResponseEntity<Void> inscrireEtudiantFormation(@PathVariable Long id, @PathVariable Long id_form) {
		System.out.println("📌 Requête GET reçue : inscrire l'étudiant " + id + " à la formation " + id_form);

		boolean isInscribed = studentService.inscrireEtudiantFormation(id, id_form);

		if (isInscribed) {
			System.out.println("✅ Inscription réussie !");
			return ResponseEntity.ok().build();
		} else {
			System.out.println("❌ Étudiant ou formation introuvable !");
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/{id}/groups")
	public ResponseEntity<List<Groupe>> getStudentGroups(@PathVariable Long id) {
		Optional<List<Groupe>> groupes = studentService.getStudentGroups(id);
		return groupes.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
	@GetMapping("/{id}/ues")
	public ResponseEntity<List<UE>> getUEsByStudentId(@PathVariable Long id) {
		List<UE> ues = studentService.getUEsByStudentId(id);
		return ResponseEntity.ok(ues);
	}

	// Inscrire un étudiant dans un groupe
	@PostMapping("/{studentId}/inscription-groupes/{groupeId}")
	public ResponseEntity<Void> inscrireEtudiantDansGroupe(
		@PathVariable Long studentId, @PathVariable Long groupeId) {

		boolean isInscribed = studentService.inscrireEtudiantDansGroupe(studentId, groupeId);

		if (isInscribed) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build(); // Si l'étudiant ou le groupe n'existe pas
		}
	}

	// Désinscrire un étudiant d'un groupe
	@DeleteMapping("/{studentId}/inscription-groupes/{groupeId}")
	public ResponseEntity<Void> desinscrireEtudiantDuGroupe(
		@PathVariable Long studentId, @PathVariable Long groupeId) {

		boolean isUnsubscribed = studentService.desinscrireEtudiantDuGroupe(studentId, groupeId);

		if (isUnsubscribed) {
			return ResponseEntity.ok().build();
		} else {
			return ResponseEntity.notFound().build(); // Si l'étudiant ou le groupe n'existe pas
		}
	}

	@PostMapping("/{id}/inscription-ues/{id_ue}")
	public ResponseEntity<Void> inscrireEtudiantUE(@PathVariable Long id, @PathVariable Long id_ue) {
		System.out.println("📌 Requête POST reçue : inscrire l'étudiant " + id + " à l'UE " + id_ue);

		boolean isInscribed = studentService.inscrireEtudiantUE(id, id_ue);

		if (isInscribed) {
			System.out.println("✅ Inscription réussie !");
			return ResponseEntity.ok().build();
		} else {
			System.out.println("❌ Étudiant ou UE introuvable !");
			return ResponseEntity.notFound().build();
		}
	}
	@DeleteMapping("/{id}/inscription-ues/{id_ue}")
	public ResponseEntity<Void> desinscrireEtudiantUE(@PathVariable Long id, @PathVariable Long id_ue) {
		System.out.println("📌 Requête DELETE reçue : désinscrire l'étudiant " + id + " de l'UE " + id_ue);

		boolean isUnsubscribed = studentService.desinscrireEtudiantUE(id, id_ue);

		if (isUnsubscribed) {
			System.out.println("✅ Désinscription réussie !");
			return ResponseEntity.ok().build();
		} else {
			System.out.println("❌ Étudiant ou UE introuvable !");
			return ResponseEntity.notFound().build();
		}
	}

 */
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return ResponseEntity.ok(students);
	}


}
