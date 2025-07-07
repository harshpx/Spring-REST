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

  public Employee save(Employee employee) {
    entityManager.persist(employee);
    return employee;
  }

  public List<Employee> getEmployees() {
    // @SuppressWarnings("unchecked")
    // List<Employee> employees = entityManager.createNativeQuery("select * from employee", Employee.class).getResultList();
    // return employees;
    TypedQuery<Employee> queryResult = entityManager.createQuery("from Employee", Employee.class);
    List<Employee> employees = queryResult.getResultList();
    return employees;
  }

  public void deleteEmployee(int id) {
    Employee emp = entityManager.find(Employee.class, id);
    entityManager.remove(emp);
  }
}
