package com.rest.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.demo.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
