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

import com.learn.RestWithDatabase.customResponses.DataResponse;
import com.learn.RestWithDatabase.customResponses.MessageResponse;
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
  public ResponseEntity<DataResponse<Employee>> createEmployee(@Valid @RequestBody CreateEmployeeRequest employeeData) {
    Employee savedEmployee = employeeService.createEmployee(employeeData);
    DataResponse<Employee> response = new DataResponse<>(savedEmployee);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // read
  @GetMapping
  public ResponseEntity<DataResponse<List<Employee>>> getEmployees() {
    DataResponse<List<Employee>> response = new DataResponse<>(employeeService.getEmployees());
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  @GetMapping("/{employeeId}")
  public ResponseEntity<DataResponse<Employee>> getEmployee(@PathVariable int employeeId) {
    Employee emp = employeeService.getEmployee(employeeId);
    DataResponse<Employee> response = new DataResponse<>(emp);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  // update
  @PutMapping
  public ResponseEntity<DataResponse<Employee>> updateEmployee(@Valid @RequestBody Employee employee) {
    Employee updatedEmployee = employeeService.updateEmployee(employee);
    DataResponse<Employee> response = new DataResponse<>(updatedEmployee);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  // patch
  @PatchMapping
  public ResponseEntity<DataResponse<Employee>> patchEmployee(@Valid @RequestBody PatchEmployeeRequest employeeData) {
    Employee patchedEmployee = employeeService.patchEmployee(employeeData);
    DataResponse<Employee> response = new DataResponse<>(patchedEmployee);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
  
  // delete
  @DeleteMapping("/{employeeId}")
  public ResponseEntity<MessageResponse> deleteEmployee(@PathVariable int employeeId) {
    employeeService.deleteEmployee(employeeId);
    MessageResponse response = new MessageResponse("Employee with Id " + employeeId + " Deleted successfully.");
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
