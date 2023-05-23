package com.rest.demo.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rest.demo.controller.util.EmployeeModelAssembler;
import com.rest.demo.entity.Employee;
import com.rest.demo.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	private EmployeeRepository employeeRepository;
	private EmployeeModelAssembler employeeModelAssembler;
	
	public EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler employeeModelAssembler) {
		this.employeeRepository = employeeRepository;
		this.employeeModelAssembler = employeeModelAssembler;
	}

	@GetMapping("/allEmployees")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@GetMapping("/employees/{id}")
	public EntityModel<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
		return employeeModelAssembler.toModel(employee);
	}

	@PostMapping("/addEmployee")
	public Employee addEmployee(@RequestBody Employee emp) {
		return employeeRepository.save(emp);
	}

	@DeleteMapping("/deleteEmployee/{id}")
	public List<Employee> deleteEmployee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
		return employeeRepository.findAll();
	}

	@PutMapping("update/{id}")
	public Employee updateOrAddEmployee(@RequestBody Employee newEmployee, @PathVariable Long id) {
		return employeeRepository.findById(id).map(emp -> {
			emp.setName(newEmployee.getName());
			emp.setRole(newEmployee.getRole());
			return employeeRepository.save(emp);
		}).orElseGet(() -> employeeRepository.save(newEmployee));
	}
}
