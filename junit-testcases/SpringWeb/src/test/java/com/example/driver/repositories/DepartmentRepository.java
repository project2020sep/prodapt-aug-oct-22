package com.example.driver.repositories;

import com.example.entity.Department;
import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
