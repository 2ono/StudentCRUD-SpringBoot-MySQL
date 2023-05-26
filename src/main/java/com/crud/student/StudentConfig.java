package com.crud.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student henry = new Student(
					2L, "Henry", "henry.janny@gmail.com",
					LocalDate.of(2013, Month.JULY, 29));
			
			Student alex = new Student(
					 "Alex", "alex@gmail.com",
					LocalDate.of(2004, Month.JULY, 29));
			
			Student jenny = new Student(
					 "Jenny", "jenny@gmail.com",
					LocalDate.of(2012, Month.AUGUST, 12));
			
			Student mira = new Student(
					 "Mira", "mira@gmail.com",
					LocalDate.of(1989, Month.SEPTEMBER, 21));
			
			repository.saveAll(
					List.of(henry, alex, jenny,mira)
					);
			
		};
	}
}
