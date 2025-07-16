package com.demo.employee_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.employee_management.entity.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {}

