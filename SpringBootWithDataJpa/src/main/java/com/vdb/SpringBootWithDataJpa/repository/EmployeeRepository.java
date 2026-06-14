package com.vdb.SpringBootWithDataJpa.repository;

import com.vdb.SpringBootWithDataJpa.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // custom methods

    Employee findByEmpEmailId(String empEmailId);

    Employee findByEmpName(String empName);

    Employee findByEmpEmailIdAndEmpPassword(String empEmailId, String empPassword);

}
