package org.example.services;

import org.example.model.Person;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PersonServiceWithRepository {

    private final UserRepository userRepository;

    @Autowired
    public PersonServiceWithRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void persist(Person person) {
        userRepository.save(person);
    }

    public void update(int id, Person person) {
        person.setID(id);
        userRepository.save(person);
    }

    public Person getUserById(int id) {
        return userRepository.findById(id).stream().findFirst().orElse(null);
    }

    public void delete(int id) {
        Optional<Person> opt = userRepository.findById(id);
        opt.ifPresent(userRepository::delete);
    }

    public List<Person> getAllUsers() {
        return userRepository.findAll();
    }
}
