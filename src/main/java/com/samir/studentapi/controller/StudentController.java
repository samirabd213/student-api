package com.samir.studentapi.controller;

import com.samir.studentapi.model.dto.StudentDTO;
import com.samir.studentapi.model.entity.Student;
import com.samir.studentapi.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/students")
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
}
