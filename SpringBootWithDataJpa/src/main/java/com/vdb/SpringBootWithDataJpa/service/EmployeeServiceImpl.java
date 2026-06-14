package com.vdb.SpringBootWithDataJpa.service;

import com.vdb.SpringBootWithDataJpa.exception.RecordNotFoundException;
import com.vdb.SpringBootWithDataJpa.model.Employee;
import com.vdb.SpringBootWithDataJpa.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee findByEmailId(String empEmailId) {
        return employeeRepository.findByEmpEmailId(empEmailId);
    }

    @Override
    public Employee signUp(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean signIn(String empEmailId, String empPassword) {

        boolean flag = false;

        Employee employee = employeeRepository.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);

        if (employee != null) {
            flag = true;
        }

        return flag;
    }

    @Override
    public Optional<Employee> findByEmpId(long empId) {
        return Optional.of(employeeRepository.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee not found")));
    }

    @Override
    public Employee findByEmpName(String empName) {
        return employeeRepository.findByEmpName(empName);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee update(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteByEmpId(long empId) {
        employeeRepository.deleteById(empId);
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
    }
}
