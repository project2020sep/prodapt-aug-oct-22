package com.example.service;

import com.example.entity.Department;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface DepartmentService {


    List<Department> getAll();
    ResponseEntity<String> insert(Department d);
    ResponseEntity<String> update(Department d, int id);
    ResponseEntity<String> delete(int deptno);
    Optional<Department> getDepartmentById(int deptno);
    ResponseEntity<String> deleteAll();
}
