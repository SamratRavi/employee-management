package com.demo.employee_management.controller;

import com.demo.employee_management.entity.Department;
import com.demo.employee_management.entity.exception.ResourceNotFoundException;
import com.demo.employee_management.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
@AutoConfigureMockMvc(addFilters = false)
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService deptService;

    @Test
    void saveDepartment_shouldSaveAndRedirect_whenDepartmentDoesNotExist() throws Exception {
        // Mock department does not exist
        Mockito.when(deptService.getDepartmentByName("Finance"))
                .thenReturn(Optional.empty());
        Mockito.when(deptService.saveDepartment(any(Department.class)))
                .thenReturn(new Department());

        mockMvc.perform(post("/department/save")
                        .param("name", "Finance")
                .with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/departments"));

        Mockito.verify(deptService).saveDepartment(Mockito.any(Department.class));
    }


}
