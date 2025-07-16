package com.demo.employee_management.service;

import com.demo.employee_management.entity.Department;
import com.demo.employee_management.repository.DepartmentRepository;
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
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void getAllDepartments_returnsList() {
        Department d1 = new Department();
        Department d2 = new Department();
        when(departmentRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Department> result = departmentService.getAllDepartments();

        assertEquals(2, result.size());
        verify(departmentRepository).findAll();
    }

    @Test
    void getDepartmentById_found() {
        Department department = new Department();
        when(departmentRepository.findById(1L)).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.getDepartmentById(1L);

        assertTrue(result.isPresent());
        assertEquals(department, result.get());
        verify(departmentRepository).findById(1L);
    }

    @Test
    void getDepartmentById_notFound() {
        when(departmentRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Department> result = departmentService.getDepartmentById(1L);

        assertFalse(result.isPresent());
        verify(departmentRepository).findById(1L);
    }

    @Test
    void getDepartmentByName_found() {
        Department department = new Department();
        when(departmentRepository.findByName("HR")).thenReturn(Optional.of(department));

        Optional<Department> result = departmentService.getDepartmentByName("HR");

        assertTrue(result.isPresent());
        verify(departmentRepository).findByName("HR");
    }

    @Test
    void saveDepartment_savesAndReturns() {
        Department department = new Department();
        when(departmentRepository.save(department)).thenReturn(department);

        Department result = departmentService.saveDepartment(department);

        assertEquals(department, result);
        verify(departmentRepository).save(department);
    }

    @Test
    void deleteDepartment_deletes() {
        departmentService.deleteDepartment(1L);

        verify(departmentRepository).deleteById(1L);
    }
}
