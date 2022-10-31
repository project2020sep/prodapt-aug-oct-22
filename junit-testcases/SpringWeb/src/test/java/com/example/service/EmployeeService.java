package com.example.service;

import com.example.entity.Employee;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> getAll();
    ResponseEntity<String> insert(Employee e);
    ResponseEntity<String> update(Employee e, int id);
    ResponseEntity<String> delete(int empno);
    Optional<Employee> getEmployeeById(int empno);
    ResponseEntity<String> deleteAll();
}