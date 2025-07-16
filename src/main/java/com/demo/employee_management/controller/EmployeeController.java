package com.demo.employee_management.controller;

import com.demo.employee_management.entity.Employee;
import com.demo.employee_management.entity.exception.ResourceNotFoundException;
import com.demo.employee_management.service.DepartmentService;
import com.demo.employee_management.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
    private final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService empService;

    @Autowired
    private DepartmentService deptService;

    @GetMapping("/employees")
    public String viewEmployees(Model model) {
        List<Employee> employees = empService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/employee/add")
    public String addEmployeeForm(Model model) {
        logger.info("Adding employee form");
        model.addAttribute("employee", new Employee());
        model.addAttribute("departments", deptService.getAllDepartments());
        return "employee_form";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        empService.saveEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/employee/edit/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        Employee emp = empService.getEmployeeById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id " + id));
        model.addAttribute("employee", emp);
        model.addAttribute("departments", deptService.getAllDepartments());
        return "employee_form";
    }

    @GetMapping("/employee/delete/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        empService.deleteEmployee(id);
        return "redirect:/employees";
    }
}

