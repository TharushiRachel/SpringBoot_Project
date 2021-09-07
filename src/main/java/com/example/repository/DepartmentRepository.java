package com.example.repository;

import com.example.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface DepartmentRepository extends JpaRepository<Department, Integer>, QuerydslPredicateExecutor<Department> {


}
