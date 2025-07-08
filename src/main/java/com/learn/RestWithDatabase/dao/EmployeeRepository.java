package com.learn.RestWithDatabase.dao;

import com.learn.RestWithDatabase.entity.Employee;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class EmployeeRepository {

  private EntityManager entityManager;
  @Autowired
  public EmployeeRepository(EntityManager entityManager) {
    this.entityManager = entityManager;
  }
  // create, update or patch
  public Employee save(Employee employee) {
    Employee newEmployee = entityManager.merge(employee);
    return newEmployee;
  }
  // read
  public List<Employee> findAll() {
    TypedQuery<Employee> queryResult = entityManager.createQuery("from Employee", Employee.class);
    List<Employee> employees = queryResult.getResultList();
    return employees;
  }
  public Employee findById(int id) {
    return entityManager.find(Employee.class, id);
  }
  // delete
  public void delete(int id) {
    Employee emp = entityManager.find(Employee.class, id);
    entityManager.remove(emp);
  }
}
