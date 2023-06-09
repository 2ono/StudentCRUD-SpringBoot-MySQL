package com.crud.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}



	public List<Student> getStudent() {
		return studentRepository.findAll();
	}



	public void addNewStudent(Student student) {
		// TODO Auto-generated method stub
		Optional<Student> studentOptional = studentRepository
				.findStudentByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}



	public void deleteStudent(Long studentId) {
		// TODO Auto-generated method stub
			boolean exists = studentRepository.existsById(studentId);
			if (!exists) {
				throw new IllegalStateException(
						"student widh id " + studentId + " does not exists");
			}
			studentRepository.deleteById(studentId);
	}


	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(studentId)
				.orElseThrow( () -> new IllegalStateException(
						"student with id " + studentId + "doen not exits"
						));
		
		if (name != null &&
				name.length() > 0 &&
				!Objects.equals(student.getName(), name)) {
		
			student.setName(name);
		}
		
		if (name != null &&
				name.length() > 0 &&
				!Objects.equals(student.getEmail(), email)) {
			Optional<Student> studentOptional = studentRepository
						.findStudentByEmail(email);
			if (studentOptional.isPresent()) {
				throw new IllegalStateException("email taken");
			}
			student.setEmail(email);
		}
			
	}
}
