package com.learn.RestWithDatabase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.RestWithDatabase.customExceptions.EmployeeNotFoundException;
import com.learn.RestWithDatabase.dao.EmployeeRepository;
import com.learn.RestWithDatabase.dto.CreateEmployeeRequest;
import com.learn.RestWithDatabase.dto.PatchEmployeeRequest;
import com.learn.RestWithDatabase.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

  private EmployeeRepository employeeRepository;

  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  // create
  @Transactional
  public Employee createEmployee(CreateEmployeeRequest employeeData) {
    Employee newEmployee = new Employee(employeeData);
    return employeeRepository.save(newEmployee);
  }

  // read
  public List<Employee> getEmployees() {
    return employeeRepository.findAll();
  }

  public Employee getEmployee(int id) {
    Employee emp = employeeRepository.findById(id);
    if (emp == null) {
      throw new EmployeeNotFoundException();
    }
    return emp;
  }

  // update
  @Transactional
  public Employee updateEmployee(Employee employee) {
    Employee existingEmployee = employeeRepository.findById(employee.getId());
    if (existingEmployee == null) {
      throw new EmployeeNotFoundException();
    }
    return employeeRepository.save(employee);
  }

  @Transactional
  public Employee patchEmployee(int employeeId, PatchEmployeeRequest employeeData) {
    // retrieve existing
    Employee existingEmployee = employeeRepository.findById(employeeId);
    if (existingEmployee == null) {
      throw new EmployeeNotFoundException();
    }
    // patch fields if exist
    if (employeeData.getFirstName() != null)
      existingEmployee.setFirstName(employeeData.getFirstName());
    if (employeeData.getLastName() != null)
      existingEmployee.setLastName(employeeData.getLastName());
    if (employeeData.getEmail() != null)
      existingEmployee.setEmail(employeeData.getEmail());
    // Save patched employee
    Employee patchedEmployee = employeeRepository.save(existingEmployee);
    return patchedEmployee;
  }

  // delete
  @Transactional
  public void deleteEmployee(int id) {
    employeeRepository.delete(id);
  }
}
