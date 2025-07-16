package com.demo.employee_management.service;

import com.demo.employee_management.entity.Employee;
import com.demo.employee_management.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void getAllEmployees_returnsList() {
        Employee e1 = new Employee();
        Employee e2 = new Employee();
        when(employeeRepository.findAll()).thenReturn(Arrays.asList(e1, e2));

        List<Employee> result = employeeService.getAllEmployees();

        assertEquals(2, result.size());
        verify(employeeRepository).findAll();
    }

    @Test
    void getEmployeeById_found() {
        Employee employee = new Employee();
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertTrue(result.isPresent());
        assertEquals(employee, result.get());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void getEmployeeById_notFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Employee> result = employeeService.getEmployeeById(1L);

        assertFalse(result.isPresent());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void getEmployeeByEmail_found() {
        Employee employee = new Employee();
        when(employeeRepository.findByEmail("test@example.com")).thenReturn(Optional.of(employee));

        Optional<Employee> result = employeeService.getEmployeeByEmail("test@example.com");

        assertTrue(result.isPresent());
        verify(employeeRepository).findByEmail("test@example.com");
    }

    @Test
    void saveEmployee_savesAndReturns() {
        Employee employee = new Employee();
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee result = employeeService.saveEmployee(employee);

        assertEquals(employee, result);
        verify(employeeRepository).save(employee);
    }

    @Test
    void deleteEmployee_deletes() {
        employeeService.deleteEmployee(1L);

        verify(employeeRepository).deleteById(1L);
    }
}
