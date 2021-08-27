package com.example.entity;

import com.example.enums.Status;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;

import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
@Entity
@Table(name="admin")
@EntityListeners(AuditingEntityListener.class)
@Where(clause = "status != 'D'")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nic;

    private String fullName;

    private String email;

    private String password;

    @Where(clause = "status != 'D'")
    private Status status;

    @OneToMany(mappedBy = "admin", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Customer> customers;

    @OneToMany(mappedBy = "admin", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Employee> employees;

    @OneToMany(mappedBy = "admin", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<EmployeeLeave> employeeLeaves;

    @CreatedDate
    @Temporal(TIMESTAMP)
    protected Date createdDate;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    protected Date lastModifiedDate;


}
