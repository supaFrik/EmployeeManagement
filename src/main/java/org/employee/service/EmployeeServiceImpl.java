package org.employee.service;

import org.employee.entity.Employee;
import org.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if(employeeRepository.findById(employee.getId()).isEmpty()) {
            throw new RuntimeException("Employee with id " + employee.getId() + " is not found !");
        }
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if(employeeRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Employee with id " + id + " is not found !");
        }
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
    }
}
