package com.samir.studentapi.service;

import com.samir.studentapi.model.dto.StudentDTO;
import com.samir.studentapi.model.entity.Groupe;
import com.samir.studentapi.model.entity.Student;
import com.samir.studentapi.model.entity.Formation;
import com.samir.studentapi.model.entity.UE;
import com.samir.studentapi.model.repository.GroupeRepository;
import com.samir.studentapi.model.repository.StudentRepository;
import com.samir.studentapi.model.repository.FormationRepository;
import com.samir.studentapi.model.repository.UERepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private FormationRepository formationRepository;
	@Autowired
	private GroupeRepository groupeRepository ;
	@Autowired
	private UERepository ueRepository;


	public Optional<Student> getStudentById(Long id) {
		return studentRepository.findById(id);
	}

	public Student createStudent(StudentDTO studentDTO) {
		Student student = new Student();
		student.setFirstName(studentDTO.getFirstName());
		student.setLastName(studentDTO.getLastName());
		student.setEmail(studentDTO.getEmail());
		student.setPassword(studentDTO.getPassword());
		return studentRepository.save(student);
	}

	public Optional<Student> updateStudent(Long id, StudentDTO studentDTO) {
		return studentRepository.findById(id).map(student -> {
			student.setFirstName(studentDTO.getFirstName());
			student.setLastName(studentDTO.getLastName());
			student.setEmail(studentDTO.getEmail());
			student.setPassword(studentDTO.getPassword());
			return studentRepository.save(student);
		});
	}

	public boolean deleteStudent(Long id) {
		if (studentRepository.existsById(id)) {
			studentRepository.deleteById(id);
			return true;
		}
		return false;
	}
	public Optional<Formation> getStudentFormation(Long studentId) {
		return studentRepository.findById(studentId).map(Student::getFormation);
	}
	// Nouvelle méthode pour inscrire un étudiant à une formation
	public boolean inscrireEtudiantFormation(Long studentId, Long formationId) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		Optional<Formation> formationOptional = formationRepository.findById(formationId);

		if (studentOptional.isPresent() && formationOptional.isPresent()) {
			Student student = studentOptional.get();
			Formation formation = formationOptional.get();

			// Vérification si l'étudiant est déjà inscrit à cette formation
			if (student.getFormation() != null && student.getFormation().equals(formation)) {
				// Si l'étudiant est déjà inscrit à cette formation, on ne fait rien
				return false;
			}

			// On ajoute l'étudiant à la formation
			formation.getEtudiants().add(student);
			formationRepository.save(formation);

			// On met à jour l'étudiant pour qu'il soit inscrit à la formation
			student.setFormation(formation);
			studentRepository.save(student);

			return true;
		}
		return false; // Si l'étudiant ou la formation n'existe pas
	}

	public Optional<List<Groupe>> getStudentGroups(Long studentId) {
		return studentRepository.findById(studentId).map(Student::getGroupes);
	}
	public List<UE> getUEsByStudentId(Long studentId) {
		Student student = studentRepository.findById(studentId)
			.orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
		return student.getUes();
	}

	public boolean inscrireEtudiantDansGroupe(Long studentId, Long groupeId) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		Optional<Groupe> groupeOptional = groupeRepository.findById(groupeId);

		if (studentOptional.isPresent() && groupeOptional.isPresent()) {
			Student student = studentOptional.get();
			Groupe groupe = groupeOptional.get();

			// Vérification si l'étudiant est déjà inscrit à ce groupe
			if (student.getGroupes().contains(groupe)) {
				// Si l'étudiant est déjà inscrit, on ne fait rien
				return false;
			}

			// On ajoute l'étudiant à la liste des groupes du groupe
			groupe.getEtudiants().add(student);

			// On ajoute le groupe à la liste des groupes de l'étudiant
			student.getGroupes().add(groupe);

			studentRepository.save(student);
			groupeRepository.save(groupe);

			return true;
		}
		return false; // Si l'étudiant ou le groupe n'existe pas
	}

	// Désinscrire un étudiant d'un groupe
	public boolean desinscrireEtudiantDuGroupe(Long studentId, Long groupeId) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		Optional<Groupe> groupeOptional = groupeRepository.findById(groupeId);

		if (studentOptional.isPresent() && groupeOptional.isPresent()) {
			Student student = studentOptional.get();
			Groupe groupe = groupeOptional.get();

			// On retire l'étudiant de la liste des groupes du groupe
			groupe.getEtudiants().remove(student);

			// On retire le groupe de la liste des groupes de l'étudiant
			student.getGroupes().remove(groupe);

			studentRepository.save(student);
			groupeRepository.save(groupe);

			return true;
		}
		return false; // Si l'étudiant ou le groupe n'existe pas
	}
	public boolean inscrireEtudiantUE(Long studentId, Long ueId) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		Optional<UE> ueOptional = ueRepository.findById(ueId);  // Assure-toi d'avoir un repository pour UE

		if (studentOptional.isPresent() && ueOptional.isPresent()) {
			Student student = studentOptional.get();
			UE ue = ueOptional.get();

			// Vérification si l'étudiant est déjà inscrit à cette UE
			if (student.getUes().contains(ue)) {
				// Si l'étudiant est déjà inscrit, on ne fait rien
				return false;
			}

			// On ajoute l'UE à l'étudiant si l'étudiant n'est pas déjà inscrit à cette UE
			student.getUes().add(ue);
			studentRepository.save(student);

			// Optionnellement, on peut aussi ajouter l'étudiant à la liste des étudiants de l'UE
			ue.getStudents().add(student);
			ueRepository.save(ue);

			return true;
		}
		return false; // Si l'étudiant ou l'UE n'existe pas
	}

	public boolean desinscrireEtudiantUE(Long studentId, Long ueId) {
		Optional<Student> studentOptional = studentRepository.findById(studentId);
		Optional<UE> ueOptional = ueRepository.findById(ueId);  // Suppose qu'il y a un repository pour UE

		if (studentOptional.isPresent() && ueOptional.isPresent()) {
			Student student = studentOptional.get();
			UE ue = ueOptional.get();

			// On retire l'UE de la liste des UEs de l'étudiant
			student.getUes().remove(ue);
			studentRepository.save(student);

			// Optionnellement, on peut retirer l'étudiant de la liste des étudiants de l'UE
			ue.getStudents().remove(student);
			ueRepository.save(ue);

			return true;
		}
		return false; // Si l'étudiant ou l'UE n'existe pas
	}
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}



}


