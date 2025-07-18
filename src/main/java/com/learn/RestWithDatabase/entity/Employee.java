package com.learn.RestWithDatabase.entity;

import com.learn.RestWithDatabase.dto.CreateEmployeeRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employee")
@Getter
@Setter
public class Employee {
  // fields
  @Id
  @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;

  @NotBlank(message = "First name is required")
  @Column(name = "firstName")
  private String firstName;

  @NotBlank(message = "Last name is required")
  @Column(name = "lastName")
  private String lastName;

  @Column(name = "email")
  private String email;

  // constructors
  public Employee() {}
  public Employee(CreateEmployeeRequest employeeData) {
    this.setId(0);
    this.firstName = employeeData.getFirstName();
    this.lastName = employeeData.getLastName();
    this.email = employeeData.getEmail();
  }
  public Employee(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String toString() {
    return "(" + this.id + " : " + this.firstName + " " + this.lastName + " : " + this.email + ")";
  }
  // getters and setters will be generated by lombok
}
