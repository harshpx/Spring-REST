package com.learn.RestWithDatabase.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.RestWithDatabase.customResponses.DataResponse;
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

  @GetMapping
  public ResponseEntity<DataResponse<List<Employee>>> getEmployees() {
    DataResponse<List<Employee>> response = new DataResponse<>(employeeService.getEmployees());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<DataResponse<Employee>> createEmployee(@Valid @RequestBody Employee employee) {
    employee.setId(0);
    Employee savedEmployee = employeeService.createEmployee(employee);
    DataResponse<Employee> response = new DataResponse<>(savedEmployee);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
