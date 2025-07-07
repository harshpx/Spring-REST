package com.learn.RestWithDatabase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.RestWithDatabase.customExceptions.EmployeeNotFoundException;
import com.learn.RestWithDatabase.dao.EmployeeRepository;
import com.learn.RestWithDatabase.dto.EmployeeRequest;
import com.learn.RestWithDatabase.entity.Employee;

import jakarta.transaction.Transactional;

@Service
public class EmployeeService {

  private EmployeeRepository employeeRepository;
  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Transactional
  public Employee createEmployee(EmployeeRequest employeeData) {
    Employee newEmployee = new Employee(employeeData);
    return employeeRepository.create(newEmployee);
  }

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

  @Transactional
  public Employee updateEmployee(int id, EmployeeRequest employeeData) {
    Employee existingEmployee = employeeRepository.get(id);
    if (existingEmployee == null) {
      throw new EmployeeNotFoundException();
    }
    existingEmployee.setFirstName(employeeData.getFirstName());
    existingEmployee.setLastName(employeeData.getLastName());
    existingEmployee.setEmail(employeeData.getEmail());
    return employeeRepository.update(existingEmployee);
  }

  @Transactional
  public void deleteEmployee(int id) {
    employeeRepository.delete(id);
  }
}
