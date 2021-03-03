package org.example.services;

import org.example.dao.PersonDAOHibernate;
import org.example.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonService {

    private final PersonDAOHibernate personDAOHibernate;

    @Autowired
    public PersonService(PersonDAOHibernate personDAOHibernate) {
        this.personDAOHibernate = personDAOHibernate;
    }

    public void persist(Person person) {
        personDAOHibernate.openCurrentSessionWithTransaction();
        personDAOHibernate.persist(person);
        personDAOHibernate.closeCurrentSessionWithTransaction();
    }

    public void update(int id, Person person) {
        personDAOHibernate.openCurrentSessionWithTransaction();
        personDAOHibernate.update(id, person);
        personDAOHibernate.closeCurrentSessionWithTransaction();
    }

    public Person getUserById(int id) {
        personDAOHibernate.openCurrentSession();
        Person person = personDAOHibernate.getUserById(id);
        personDAOHibernate.closeCurrentSession();
        return person;
    }

    public void delete(int id) {
        personDAOHibernate.openCurrentSessionWithTransaction();
        personDAOHibernate.delete(id);
        personDAOHibernate.closeCurrentSessionWithTransaction();
    }

    public List<Person> getAllUsers() {
        personDAOHibernate.openCurrentSession();
        List<Person> people = personDAOHibernate.getAllUsers();
        personDAOHibernate.closeCurrentSession();
        return people;
    }
}
