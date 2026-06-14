package com.vdb.SpringBootWithDataJpa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Employee_DB")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long empId;

    private String empName;

    private String empAddress;

    private long empSalary;

    @Column(unique = true)
    private long empContactNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date empDOB;

    @Column(unique = true)
    private String empEmailId;

    private String empPassword;

}
