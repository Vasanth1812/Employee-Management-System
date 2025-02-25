package com.example.CMS.Entity;

import jakarta.persistence.*;
import org.hibernate.boot.registry.selector.spi.StrategySelector;
import org.springframework.boot.autoconfigure.web.WebProperties;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name="first_Name")
    private String firstName;
    @Column(name="lastName")
    private String lastName;
    @Column(name="e-mail" , nullable = false , unique = true)
    private String email;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employee(long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Employee() {
        // Default constructor required by JPA
    }
}
