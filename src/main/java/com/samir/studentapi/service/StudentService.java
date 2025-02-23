package com.samir.studentapi.service;

import com.samir.studentapi.model.dto.StudentDTO;
import com.samir.studentapi.model.entity.Groupe;
import com.samir.studentapi.model.entity.Student;
import com.samir.studentapi.model.entity.Formation;
import com.samir.studentapi.model.repository.StudentRepository;
import com.samir.studentapi.model.repository.FormationRepository;
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

}
