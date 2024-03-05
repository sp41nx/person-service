package telran.java51.person.service;

import java.util.List;

import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.PopulationDto;
import telran.java51.person.model.Address;

public interface PersonService {

	Boolean addPerson(PersonDto personDto);
	
	PersonDto findPersonById(Integer id);

	List<PersonDto> findPersonsByCity(String city);

	List<PersonDto> findPersonsBetweenAges(Integer min, Integer max);

	PersonDto updatePersonName(Integer id, String name);

	List<PersonDto> findPersonsByName(String name);

	List<PopulationDto> getCityPopulation();

	PersonDto updatePersonAddress(Integer id, Address address);

	PersonDto deletePerson(Integer id);
}
