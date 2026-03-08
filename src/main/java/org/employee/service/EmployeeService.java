package org.employee.service;

import org.employee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface EmployeeService {
    void deleteEmployeeById(Long id);
    void saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
    Employee getEmployeeById(Long id);
    Page<Employee> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection);
}
