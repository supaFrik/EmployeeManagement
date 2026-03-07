package org.employee.controller;

import org.employee.entity.Employee;
import org.employee.service.EmployeeService;
import org.employee.service.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String listEmployee(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employees";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "employee-form";
    }

    @PostMapping
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.createEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee-edit";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.updateEmployee(employee);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
