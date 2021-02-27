package org.example.dao;

import org.example.model.Person;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Данный паттерн используется для отделения объекта от
 * логики взаимодействия с Базой данных
 */

@Component
public class PersonDAO {
    private int ID_COUNTER = 0;

    private final List<Person> people;

    /**
     * List.of() returns immutable object - REMEMBER IT ! ! ! <(-_-)>
     * @return
     */
    private List<Person> initPeople() {
        ArrayList<Person> arr = new ArrayList<>();
        arr.add(new Person(ID_COUNTER,"Eduard", "Matveev", 24));
        arr.add(new Person(++ID_COUNTER,"Artak", "Kirakosyan", 24));
        arr.add(new Person(++ID_COUNTER,"Mkrtich", "Mkhitaryan", 23));
        arr.add( new Person(++ID_COUNTER,"Andranik", "Nanagulyan", 24));
        arr.add(new Person(++ID_COUNTER,"Tatyana", "Yudaeva", 50));
        arr.add(new Person(++ID_COUNTER,"Zograb", "Matveev", 51));
        arr.add( new Person(++ID_COUNTER,"Viktoria", "Matveeva", 28));

        return arr;
    }

    public PersonDAO() {
        people = initPeople();
    }

    public List<Person> getPeople() {
        return people;
    }

    public Person getPersonByID(int id) {
        return people.stream().filter(personName -> id == personName.getID()).findAny().orElse(null);
    }

    public void save(Person person) {
        person.setID(++ID_COUNTER);
        people.add(person);
    }

    public void editPerson(int id, Person person) {
        Person newPerson = getPersonByID(id);
        newPerson.setName(person.getName());
        newPerson.setAge(person.getAge());
        newPerson.setSurname(person.getSurname());
    }

    public void deleteById(int id) {
        people.removeIf(person -> id == person.getID());
    }
}
