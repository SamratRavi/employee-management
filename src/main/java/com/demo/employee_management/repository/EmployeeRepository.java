package com.demo.employee_management.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.employee_management.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}

