package com.learn.RestWithDatabase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.RestWithDatabase.customExceptions.EmployeeNotFoundException;
import com.learn.RestWithDatabase.dao.EmployeeRepository;
import com.learn.RestWithDatabase.dto.CreateEmployeeRequest;
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
    return employeeRepository.getAll();
  }

  public Employee getEmployee(int id) {
    Employee emp = employeeRepository.get(id);
    if (emp == null) {
      throw new EmployeeNotFoundException();
    }
    return emp;
  }

  //update
  @Transactional
  public Employee updateEmployee(Employee employee) {
    Employee existingEmployee = employeeRepository.get(employee.getId());
    if (existingEmployee == null) {
      throw new EmployeeNotFoundException();
    }
    return employeeRepository.save(employee);
  }

  // delete
  @Transactional
  public void deleteEmployee(int id) {
    employeeRepository.delete(id);
  }
}
