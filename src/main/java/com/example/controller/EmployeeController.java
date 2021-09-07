package com.example.controller;

import com.example.dto.request.EmployeeCreateRequest;
import com.example.dto.response.EmployeeCreateResponse;
import com.example.dto.response.EmployeeSuggestionResponse;
import com.example.entity.Employee;
import com.example.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("${app.endpoint.employeeCreate}")
    public ResponseEntity<Object> saveEmployee(@Validated @RequestBody EmployeeCreateRequest request) throws Exception {
        Employee employee = modelMapper.map(request, Employee.class);
        Employee saveEmployee = employeeService.save(employee);
        EmployeeCreateResponse employeeCreateResponse = modelMapper.map(saveEmployee, EmployeeCreateResponse.class);
        return new ResponseEntity<>(employeeCreateResponse, HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.employeeSuggestion}")
    public ResponseEntity<List<EmployeeSuggestionResponse>> listEmployee() {
        List<Employee> employeeList = employeeService.getEmployees();
        List<EmployeeSuggestionResponse> employeeSuggestionResponses = employeeList.stream().map(employee -> modelMapper.map(employee, EmployeeSuggestionResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(employeeSuggestionResponses, HttpStatus.OK);
    }
}
