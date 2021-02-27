package org.example.dao;

import org.example.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Component
public class PersonDAO {

    private final List<Person> people;

    private List<Person> initPeople() {
        return List.of(new Person("Eduard", "Matveev", 24),
                new Person("Artak", "Kirakosyan", 24),
                new Person("Mkrtich", "Mkhitaryan", 23),
                new Person("Andranik", "Nanagulyan", 24),
                new Person("Tatyana", "Yudaeva", 50),
                new Person("Zograb", "Matveev", 51),
                new Person("Viktoria", "Matveeva", 28));
    }

    public PersonDAO() {
        people = initPeople();
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPersonByName(String name) {
        return people.stream().filter(personName ->
                name.equals(personName.getName())).findAny()
                .orElse(null);
    }
}
