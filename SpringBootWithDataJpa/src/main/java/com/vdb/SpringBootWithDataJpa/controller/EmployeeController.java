package com.vdb.SpringBootWithDataJpa.controller;

import com.vdb.SpringBootWithDataJpa.exception.RecordAlreadyExistsException;
import com.vdb.SpringBootWithDataJpa.exception.RecordNotFoundException;
import com.vdb.SpringBootWithDataJpa.model.Employee;
import com.vdb.SpringBootWithDataJpa.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@Slf4j
public class EmployeeController {

    @Autowired
    private IEmployeeService iEmployeeService;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@RequestBody Employee employee) {

        Employee employee1 = iEmployeeService.findByEmailId(employee.getEmpEmailId());

        if (employee1 != null) {
            throw new RecordAlreadyExistsException("Employee Already exists");
        }

        log.info("@@@@ trying to register employee {}", employee.getEmpName());
        return ResponseEntity.ok(iEmployeeService.signUp(employee));
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(iEmployeeService.signIn(empEmailId, empPassword));
    }

    @GetMapping("/findbyempid/{empId}")
    public ResponseEntity<Optional<Employee>> findByEmpId(@PathVariable long empId) {
        return ResponseEntity.ok(iEmployeeService.findByEmpId(empId));
    }

    @GetMapping("/findbyempname/{empName}")
    public ResponseEntity<Employee> findByEmpName(@PathVariable String empName) {
        return ResponseEntity.ok(iEmployeeService.findByEmpName(empName));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAllEmployee() {
        return ResponseEntity.ok(iEmployeeService.findAll());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<?> updateEmployee(@PathVariable long empId, @RequestBody Employee employee) {
        Employee employee1 = iEmployeeService.findByEmpId(empId).orElseThrow(() -> new RecordNotFoundException("Employee Id Not Found"));

        employee1.setEmpName(employee.getEmpName());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpContactNumber(employee.getEmpContactNumber());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpSalary(employee.getEmpSalary());
        employee1.setEmpPassword(employee.getEmpPassword());

        iEmployeeService.update(employee1);

        return ResponseEntity.ok("Employee Updated Successfully");
    }

    // find by DOB

    @GetMapping("/findbydob/{empDOB}")
    public ResponseEntity<List<Employee>> findByDOB(@PathVariable String empDOB) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return ResponseEntity.ok(iEmployeeService.findAll().stream().filter(emp -> simpleDateFormat.format(emp.getEmpDOB()).equals(empDOB)).toList());
    }

    // find by any input
    @GetMapping("/findbyanyinput/{input}")
    public ResponseEntity<List<Employee>> findByAnyInput(@PathVariable String input) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        return ResponseEntity.ok(
                iEmployeeService.findAll()
                        .stream()
                        .filter(emp ->
                                sdf.format(emp.getEmpDOB()).equals(input)
                                        || emp.getEmpAddress().equalsIgnoreCase(input)
                                        || emp.getEmpEmailId().equalsIgnoreCase(input)
                                        || emp.getEmpName().equalsIgnoreCase(input)
                                        || emp.getEmpPassword().equals(input)
                                        || String.valueOf(emp.getEmpContactNumber()).trim().equals(input.trim())
                                        || String.valueOf(emp.getEmpSalary()).trim().equals(input.trim())
                        )
                        .toList()
        );
    }

    // sort by salary
    @GetMapping("/sortbysalary")
    public ResponseEntity<List<Employee>> sortBySalary() {
        return ResponseEntity.ok(iEmployeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpSalary)).toList());
    }


    // patch mapping
    @PatchMapping("/changename/{empId/}{empName}")
    public ResponseEntity<Employee> ChangeEmpName(@PathVariable long empId, @PathVariable String empName) {

        Employee employee = iEmployeeService.findByEmpId(empId).orElseThrow(() -> new RecordNotFoundException("Employee Not Found"));

        employee.setEmpName(empName);

        return ResponseEntity.ok(iEmployeeService.update(employee));
    }

    // sort in descending order

    @GetMapping("/sortbysalarydesc")
    public ResponseEntity<List<Employee>> sortBySalaryInDesc() {
        return ResponseEntity.ok(iEmployeeService.findAll().stream().sorted(Comparator.comparing(Employee::getEmpSalary).reversed()).toList());
    }


    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteByEmpId(@PathVariable long empId) {
        iEmployeeService.deleteByEmpId(empId);

        return ResponseEntity.ok("Employee Deleted Successfully");
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<String> deleteAll() {
        iEmployeeService.deleteAll();

        return ResponseEntity.ok("All Employee Deleted Successfully");
    }

}
