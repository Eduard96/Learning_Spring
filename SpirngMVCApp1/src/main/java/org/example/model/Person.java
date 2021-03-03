package org.example.model;

import javax.persistence.*;
import javax.validation.constraints.*;

/**
 * POJO - Plain Old Java Object
 */
@Entity
@Table(name = "Person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID = 0;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 4, max = 30, message = "Name should be between 2 and 30 characters")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Surname should not be empty")
    @Size(min = 4, max = 30, message = "Surname should be between 2 and 30 characters")
    @Column(name = "surname")
    private String surname;

    @PositiveOrZero(message = "Age should be greater than 0")
    @Column(name = "age")
    private int age;

    public Person() {
    }

    public Person(int ID, String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
