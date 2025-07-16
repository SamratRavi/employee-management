package com.demo.employee_management.entity;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    void testIdGetterAndSetter() {
        Department dept = new Department();
        dept.setId(10L);
        assertEquals(10L, dept.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        Department dept = new Department();
        dept.setName("Finance");
        assertEquals("Finance", dept.getName());
    }

    @Test
    void testEmployeesGetterAndSetter() {
        Department dept = new Department();
        Set<Employee> employees = new HashSet<>();
        Employee emp = new Employee();
        employees.add(emp);

        dept.setEmployees(employees);
        assertEquals(employees, dept.getEmployees());
        assertTrue(dept.getEmployees().contains(emp));
    }

    @Test
    void testDefaultValues() {
        Department dept = new Department();
        assertNull(dept.getId());
        assertNull(dept.getName());
        assertNull(dept.getEmployees());
    }
}
