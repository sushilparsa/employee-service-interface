package com.aiod.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Document(collection = "employees")
public class Employee {
    @Id
    private String empId;

    @NotBlank
    @Size(max = 10,message = "Max length of employee 10 chars")
    private String name;

    private int age;
    private String gender;
    private int salary;

    @NotNull
    private Date createdAt = new Date();

    public Employee() {

    }

    public Employee(String empId, String name, int age, String gender, int salary) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    public String getEmpid() {
        return empId;
    }

    public void setEid(String empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
