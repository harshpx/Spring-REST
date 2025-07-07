package com.learn.RestWithDatabase.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.RestWithDatabase.dao.EmployeeRepository;
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
  public Employee createEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }

  public List<Employee> getEmployees() {
    return employeeRepository.getEmployees();
  }

  @Transactional
  public void deleteEmployee(int id) {
    employeeRepository.deleteEmployee(id);
  }
}
