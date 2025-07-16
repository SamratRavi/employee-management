package com.demo.employee_management.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testIdGetterAndSetter() {
        Employee emp = new Employee();
        emp.setId(5L);
        assertEquals(5L, emp.getId());
    }

    @Test
    void testNameGetterAndSetter() {
        Employee emp = new Employee();
        emp.setName("John Doe");
        assertEquals("John Doe", emp.getName());
    }

    @Test
    void testEmailGetterAndSetter() {
        Employee emp = new Employee();
        emp.setEmail("john@example.com");
        assertEquals("john@example.com", emp.getEmail());
    }

    @Test
    void testDepartmentGetterAndSetter() {
        Employee emp = new Employee();
        Department dept = new Department();
        dept.setName("IT");
        emp.setDepartment(dept);
        assertEquals(dept, emp.getDepartment());
        assertEquals("IT", emp.getDepartment().getName());
    }

    @Test
    void testDefaultValues() {
        Employee emp = new Employee();
        assertNull(emp.getId());
        assertNull(emp.getName());
        assertNull(emp.getEmail());
        assertNull(emp.getDepartment());
    }
}
