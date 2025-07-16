package com.demo.employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.employee_management.entity.Employee;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
}
