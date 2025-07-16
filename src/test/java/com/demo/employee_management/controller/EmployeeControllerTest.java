import com.demo.employee_management.EmployeeManagementApplication;
import com.demo.employee_management.controller.EmployeeController;
import com.demo.employee_management.entity.Department;
import com.demo.employee_management.entity.Employee;
import com.demo.employee_management.service.DepartmentService;
import com.demo.employee_management.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
@ContextConfiguration(classes = EmployeeManagementApplication.class)
@AutoConfigureMockMvc(addFilters = false)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private DepartmentService departmentService;

    @Test
    void viewEmployees_shouldReturnEmployeesViewWithModel() throws Exception {
        Department department = new Department();
        department.setName("HR");
        Employee emp1 = new Employee();
        emp1.setDepartment(department);
        Employee emp2 = new Employee();
        emp2.setDepartment(department);
        List<Employee> mockEmployees = Arrays.asList(emp1, emp2);
        Mockito.when(employeeService.getAllEmployees()).thenReturn(mockEmployees);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(view().name("employees"))
                .andExpect(model().attributeExists("employees"));
    }


    @Test
    void showCreateEmployeeForm_shouldReturnEmployeeFormView() throws Exception {
        Mockito.when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(new Department()));
        mockMvc.perform(get("/employee/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee_form"))
                .andExpect(model().attributeExists("departments"));
    }

    @Test
    void saveEmployee_shouldRedirectOnSuccess() throws Exception {
        Mockito.when(employeeService.saveEmployee(any(Employee.class))).thenReturn(new Employee());
        mockMvc.perform(post("/employee/save")
                        .param("name", "John Doe")
                        .param("email", "john@example.com")
                        .param("department.id", "1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));
    }

    @Test
    void showEditEmployeeForm_shouldReturnEmployeeFormView() throws Exception {
        Employee emp = new Employee();
        emp.setId(1L);
        Mockito.when(employeeService.getEmployeeById(1L)).thenReturn(Optional.of(emp));
        Mockito.when(departmentService.getAllDepartments()).thenReturn(Arrays.asList(new Department()));
        mockMvc.perform(get("/employee/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("employee_form"))
                .andExpect(model().attributeExists("employee"))
                .andExpect(model().attributeExists("departments"));
    }

    @Test
    void deleteEmployee_shouldRedirect() throws Exception {
        mockMvc.perform(get("/employee/delete/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/employees"));
        Mockito.verify(employeeService).deleteEmployee(1L);
    }
}