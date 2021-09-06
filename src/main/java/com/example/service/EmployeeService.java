package com.example.service;

import com.example.entity.Employee;
import java.util.List;

public interface EmployeeService {

    Employee save(Employee employee) throws Exception;

    List<Employee> getEmployees();
}
