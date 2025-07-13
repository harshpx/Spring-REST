package com.learn.RestWithDatabase.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.RestWithDatabase.dto.CommonResponse;
import com.learn.RestWithDatabase.dto.CreateEmployeeRequest;
import com.learn.RestWithDatabase.dto.PatchEmployeeRequest;
import com.learn.RestWithDatabase.entity.Employee;
import com.learn.RestWithDatabase.services.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

  private EmployeeService employeeService;

  @Autowired
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  // create
  @PostMapping
  public ResponseEntity<CommonResponse<Employee>> createEmployee(
      @Valid @RequestBody CreateEmployeeRequest employeeData) {
    Employee savedEmployee = employeeService.createEmployee(employeeData);
    CommonResponse<Employee> response = new CommonResponse<>(savedEmployee);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // read
  @GetMapping
  public ResponseEntity<CommonResponse<List<Employee>>> getEmployees() {
    CommonResponse<List<Employee>> response = new CommonResponse<>(employeeService.getEmployees());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/{employeeId}")
  public ResponseEntity<CommonResponse<Employee>> getEmployee(@PathVariable int employeeId) {
    Employee emp = employeeService.getEmployee(employeeId);
    CommonResponse<Employee> response = new CommonResponse<>(emp);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // update
  @PutMapping
  public ResponseEntity<CommonResponse<Employee>> updateEmployee(@Valid @RequestBody Employee employee) {
    Employee updatedEmployee = employeeService.updateEmployee(employee);
    CommonResponse<Employee> response = new CommonResponse<>(updatedEmployee);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // patch
  @PatchMapping("/{employeeId}")
  public ResponseEntity<CommonResponse<Employee>> patchEmployee(@PathVariable int employeeId,
      @RequestBody PatchEmployeeRequest employeeData) {
    Employee patchedEmployee = employeeService.patchEmployee(employeeId, employeeData);
    CommonResponse<Employee> response = new CommonResponse<>(patchedEmployee);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // delete
  @DeleteMapping("/{employeeId}")
  public ResponseEntity<CommonResponse<String>> deleteEmployee(@PathVariable int employeeId) {
    employeeService.deleteEmployee(employeeId);
    CommonResponse<String> response = new CommonResponse<>("Employee with Id " + employeeId + " Deleted successfully.");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
