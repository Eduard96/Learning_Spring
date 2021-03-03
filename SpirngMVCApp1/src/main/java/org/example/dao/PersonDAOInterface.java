package org.example.dao;

import org.example.model.Person;

import java.util.List;

public interface PersonDAOInterface {
    List<Person> getAllUsers();

    Person getUserById(int id);

    void persist(Person person);

    void update(int id, Person person);

    void delete(int id);
}
