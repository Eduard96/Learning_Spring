package org.example.dao;

import org.example.model.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAOHibernate implements PersonDAOInterface {

    private final SessionFactory sessionFactory;
    private Transaction currentTransaction;
    private Session currentSession;

    @Autowired
    public PersonDAOHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void openCurrentSession() {
        setCurrentSession(sessionFactory.openSession());
    }

    public void closeCurrentSession() {
        getCurrentSession().close();
    }

    public void openCurrentSessionWithTransaction() {
        setCurrentSession(sessionFactory.openSession());
        setCurrentTransaction(currentSession.beginTransaction());
    }

    public void closeCurrentSessionWithTransaction() {
        getCurrentTransaction().commit();
        getCurrentSession().close();
    }


    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    @Override
    public List<Person> getAllUsers() {
        return getCurrentSession().createQuery("from Person", Person.class).list();
    }

    @Override
    public Person getUserById(int id) {
        return getCurrentSession().get(Person.class, id);
    }

    @Override
    public void persist(Person person) {
        getCurrentSession().persist(person);
        person.setName("Because persist blet");
    }

    @Override
    public void update(int id, Person person) {
        person.setID(id);
        getCurrentSession().update(person);
    }

    @Override
    public void delete(int id) {
        getCurrentSession().delete(getUserById(id));
    }
}
