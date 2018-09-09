package com.johnwaithaka.angel.payroll;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Employee {

    @Id @GeneratedValue
    private Long id;
    private String firstname;
    private String lasname;
    private String description;

    private Employee(){}

    public Employee(String firstname, String lasname, String description) {
        this.firstname = firstname;
        this.lasname = lasname;
        this.description = description;
    }
}
