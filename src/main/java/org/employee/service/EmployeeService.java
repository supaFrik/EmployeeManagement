package org.employee.service;

import org.employee.entity.Employee;
import org.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public Employee updateEmployee(Employee employee) {
        if(!employeeRepository.findById(employee.getId()).isPresent()) {
            throw new RuntimeException("Employee with id " + employee.getId() + " is not found !");
        }
        return employeeRepository.save(employee);
    }
    public void deleteEmployeeById(Long id) {
        if(employeeRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Employee with id " + id + " is not found !");
        }
        employeeRepository.deleteById(id);
    }
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
    }
}
