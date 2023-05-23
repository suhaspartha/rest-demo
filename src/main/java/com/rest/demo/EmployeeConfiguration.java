package com.rest.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rest.demo.entity.Employee;
import com.rest.demo.repository.EmployeeRepository;

@Configuration
public class EmployeeConfiguration {

	Logger logger = LoggerFactory.getLogger(EmployeeConfiguration.class);
	@Autowired EmployeeRepository employeeRepository;
	
	@Bean
	CommandLineRunner initDatabase() {
		return args -> {
			logger.info("Preloading: " + employeeRepository.save(new Employee("Suhas", "Developer")));
			logger.info("Preloading: " + employeeRepository.save(new Employee("Varsh", "QA")));
		};
		
	}
}
