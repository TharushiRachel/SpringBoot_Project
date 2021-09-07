package com.example.service.impl;

import com.example.entity.Department;
import com.example.entity.QDepartment;
import com.example.exception.ApiRequestException;
import com.example.repository.DepartmentRepository;
import com.example.service.DepartmentService;
import com.querydsl.core.BooleanBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Department save(Department department) throws Exception {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(QDepartment.department.name.eq(department.getName()));
        List<Department> departments = (List<Department>) departmentRepository.findAll(booleanBuilder);

        if (departments.size() > 0) {
            throw new ApiRequestException("Department Already Exists");
        }

        return departmentRepository.save(department);
    }

    @Transactional(readOnly = true)
    @Override
    public List<Department> getDepartmentList() {
        return departmentRepository.findAll();
    }
}
