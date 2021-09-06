package com.example.repository;

import com.example.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Integer>, QuerydslPredicateExecutor<Department> {


}
