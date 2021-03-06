package com.recreations.service.impl;

import com.recreations.model.Person;
import com.recreations.model.Training;
import com.recreations.repository.PersonDAO;
import com.recreations.service.PersonService;
import com.recreations.service.exception.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {
  @Autowired private PersonDAO personRepository;

  @Override
  public List<Person> getAll() {
    return personRepository.findAll();
  }

  @Override
  public Person get(String personID)  {
    return Optional.ofNullable(personRepository.findOne(personID)).orElseThrow(EntityNotFoundException::new);
  }

  @Override
  public Person add(Person newPerson) {
    return personRepository.save(newPerson);
  }

  @Override
  public Person remove(String obj) {
    Person p = get(obj);
    personRepository.delete(obj);
    return p;
  }

  @Override
  public void update(Person person) {
    personRepository.saveAndFlush(person);
  }

}
