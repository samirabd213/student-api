package com.samir.studentapi.service;

import com.samir.studentapi.model.dto.StudentDTO;
import com.samir.studentapi.model.entity.Student;
import com.samir.studentapi.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;


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
}
