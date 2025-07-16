package com.demo.employee_management.service;

import com.demo.employee_management.entity.Employee;
import com.demo.employee_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository empRepo;

    public List<Employee> getAllEmployees() {
        return empRepo.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return empRepo.findById(id);
    }

    public Optional<Employee> getEmployeeByEmail(String email) {
        return empRepo.findByEmail(email);
    }

    public Employee saveEmployee(Employee employee) {
        return empRepo.save(employee);
    }

    public void deleteEmployee(Long id) {
        empRepo.deleteById(id);
    }
}
