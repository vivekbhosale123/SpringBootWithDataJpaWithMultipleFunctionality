package com.vdb.SpringBootWithDataJpa.service;


import com.vdb.SpringBootWithDataJpa.model.Employee;

import java.util.List;
import java.util.Optional;

public interface IEmployeeService {

    Employee findByEmailId(String empEmailId);

    Employee signUp(Employee employee);

    boolean signIn(String empEmailId, String empPassword);

    Optional<Employee> findByEmpId(long empId);

    Employee findByEmpName(String empName);

    List<Employee> findAll();

    Employee update(Employee employee);

    void deleteByEmpId(long empId);

    void deleteAll();

}
