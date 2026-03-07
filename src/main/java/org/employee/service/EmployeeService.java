package org.employee.service;

import org.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void  deleteEmployeeById(Long id);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
}
