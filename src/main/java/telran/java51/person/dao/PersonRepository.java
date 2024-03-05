package telran.java51.person.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import telran.java51.person.model.Person;


public interface PersonRepository extends CrudRepository<Person, Integer> {

}
