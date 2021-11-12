package com.example.service.impl;

import com.example.entity.Admin;
import com.example.entity.Department;
import com.example.entity.Employee;
import com.example.entity.QEmployee;
import com.example.exception.ApiRequestException;
import com.example.repository.AdminRepository;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import com.example.service.EmployeeService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Employee save(Employee employee) throws Exception {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QEmployee.employee.nic.eq(employee.getNic()));
        List<Employee> employees = (List<Employee>) employeeRepository.findAll(booleanBuilder);

        if (employees.size() > 0) {
            throw new ApiRequestException("Employees Already Exists");
        }

        Admin adminDb = adminRepository.findById(employee.getAdmin().getId()).orElseThrow(() -> {
            throw new ApiRequestException("Admin with " + employee.getAdmin().getId() + " does not exists");
        });
        employee.setAdmin(adminDb);


        List<Department> list = employee.getDepartments().stream().map(department ->
                departmentRepository.findById(department.getId()).get()
        ).collect(Collectors.toList());

        employee.setDepartments(new HashSet<>(list));

        return employeeRepository.save(employee);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }


}
