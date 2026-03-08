package org.employee.controller;

import org.employee.entity.Employee;
import org.employee.service.EmployeeService;
import org.employee.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        return findPaginated(1, "firstName", "asc", model);
    }

    @GetMapping("/showNewEmployeeForm")
    public String showNewEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "new_employee";
    }

    @PostMapping("/saveEmployee")
    public String saveEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        try {
            employeeService.saveEmployee(employee);
        } catch (IllegalArgumentException ex) {
            model.addAttribute("employee", employee);
            model.addAttribute("error", ex.getMessage());
            return employee.getId() == null ? "new_employee" : "update_employee";
        }
        return "redirect:/employees/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String updateEmployee(@PathVariable(value = "id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "update_employee";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees/";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable("pageNo") int pageNo,
                       @RequestParam(value = "sortField", defaultValue = "firstName") String sortField,
                       @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir, Model model) {
        int pageSize = 8;
        Page<Employee> page = employeeService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Employee> employees = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("employees", employees);
        
        return "index";
    }
}
