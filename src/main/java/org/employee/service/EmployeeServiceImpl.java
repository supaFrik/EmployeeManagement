package org.employee.service;

import org.employee.entity.Employee;
import org.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void saveEmployee(Employee employee) {
        String email = employee.getEmail();
        if (email != null) {
            employeeRepository.findByEmail(email).ifPresent(existing -> {
                if (employee.getId() == null || !existing.getId().equals(employee.getId())) {
                    throw new IllegalArgumentException("Email already exists");
                }
            });
        }
        this.employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(Long id) {
        if(employeeRepository.findById(id).isEmpty()) {
            throw new RuntimeException("Employee with id " + id + " is not found !");
        }
        this.employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee with id " + id + " not found"));
    }

    @Override
    public Page<Employee> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.employeeRepository.findAll(pageable);
    }
}
