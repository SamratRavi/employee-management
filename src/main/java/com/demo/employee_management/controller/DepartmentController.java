package com.demo.employee_management.controller;


import com.demo.employee_management.entity.Department;
import com.demo.employee_management.entity.exception.ResourceNotFoundException;
import com.demo.employee_management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentService deptService;


    @GetMapping("/departments")
    public String viewDepartments(Model model) {
        List<Department> department = deptService.getAllDepartments();
        model.addAttribute("departments", department);
        return "departments";
    }

    @GetMapping("/department/add")
    public String addDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        return "department_form";
    }

    @PostMapping("/department/save")
    public String saveDepartment(@ModelAttribute Department department, Model model) {
        if (deptService.getDepartmentByName(department.getName()).isPresent()) {
            model.addAttribute("errorMessage", "Department with this name already exists!");
            return "department_form";
            //throw new ResourceNotFoundException("Department with this name already exists " );
        }
        deptService.saveDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/department/edit/{id}")
    public String editDepartment(@PathVariable Long id, Model model) {
        Department dept = deptService.getDepartmentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id " + id));
        model.addAttribute("department", dept);
        return "department_form";
    }

    @GetMapping("/department/delete/{id}")
    public String deleteDepartment(@PathVariable Long id) {
        deptService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
