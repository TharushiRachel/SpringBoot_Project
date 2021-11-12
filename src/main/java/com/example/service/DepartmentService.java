package com.example.service;

import com.example.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department save(Department department) throws Exception;

    List<Department> getDepartmentList();
}
