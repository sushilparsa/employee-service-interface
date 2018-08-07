package com.aiod.employee.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "employees")
public class Employee {
    /**
	 *
	 */
	
	private static final String MAX_LENGTH_OF_EMPLOYEE_NAME_IS_20_CHARS = "Max length of employee name is 20 chars";

	@Id
    private String id;

    @NotBlank
    @Size(max = 20, message = MAX_LENGTH_OF_EMPLOYEE_NAME_IS_20_CHARS)
    private String name;
    
    @Min(value = 5, message = "Emp Id should not be less than 5")
    @Max(value = 10, message = "Emp Id should not be greater than 10")
    private Long empId;

    private int age;
    private String gender;
    private int salary;

    @NotNull
    private Date createdAt = new Date();

    public Employee() {

    }

    public Employee(Long empId, String name, int age, String gender, int salary) {
        this.empId = empId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
    }

    /**
     * @return the empId
     */
    public Long getEmpId() {
        return empId;
    }

    /**
     * @param empId the empId to set
     */
    public void setEmpId(Long empId) {
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
