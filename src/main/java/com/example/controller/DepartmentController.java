package com.example.controller;

import com.example.dto.request.DepartmentCreateRequest;
import com.example.dto.response.AdminSuggestionResponse;
import com.example.dto.response.DepartmentCreateResponse;
import com.example.dto.response.DepartmentSuggestionResponse;
import com.example.entity.Department;
import com.example.service.DepartmentService;
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
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("${app.endpoint.departmentCreate}")
    public ResponseEntity<Object> saveDepartment(@Validated @RequestBody DepartmentCreateRequest request) throws Exception{
        Department department = modelMapper.map(request, Department.class);
        Department saveDepartment = departmentService.save(department);
        DepartmentCreateResponse departmentCreateResponse = modelMapper.map(saveDepartment, DepartmentCreateResponse.class);
        return new ResponseEntity<>(departmentCreateResponse, HttpStatus.CREATED);
    }

    @GetMapping("${app.endpoint.departmentSuggestion}")
    public ResponseEntity<List<DepartmentSuggestionResponse>> listDepartments(){
        List<Department> departmentList = departmentService.getDepartmentList();
        List<DepartmentSuggestionResponse> departmentSuggestionResponses = departmentList.stream().map(department -> modelMapper.map(department, DepartmentSuggestionResponse.class)).collect(Collectors.toList());
        return new ResponseEntity<>(departmentSuggestionResponses, HttpStatus.OK);
    }

}
