package telran.java51.person.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dao.PersonRepository;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.PopulationDto;
import telran.java51.person.exceptions.PersonNotFoundException;
import telran.java51.person.model.Address;
import telran.java51.person.model.Person;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

	final PersonRepository personRepository;
	final ModelMapper modelMapper;

	@Override
	public Boolean addPerson(PersonDto personDto) {
		if (personRepository.existsById(personDto.getId())) {
			return false;
		}
		personRepository.save(modelMapper.map(personDto, Person.class));
		return true;
	}

	@Override
	public PersonDto findPersonById(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findPersonsByCity(String city) {
		return StreamSupport.stream(personRepository.findAll().spliterator(), false)
				.filter(p -> p.getAddress().getCity().equalsIgnoreCase(city))
				.map(p -> modelMapper.map(p, PersonDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PersonDto> findPersonsBetweenAges(Integer min, Integer max) {
		return StreamSupport.stream(personRepository.findAll().spliterator(), false)
				.filter(p -> calcAge(p) >= min && calcAge(p) <= max).map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public PersonDto updatePersonName(Integer id, String name) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setName(name);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public List<PersonDto> findPersonsByName(String name) {
		return StreamSupport.stream(personRepository.findAll().spliterator(), false)
				.filter(p -> p.getName().equalsIgnoreCase(name)).map(p -> modelMapper.map(p, PersonDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<PopulationDto> getCityPopulation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PersonDto updatePersonAddress(Integer id, Address address) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		person.setAddress(address);
		personRepository.save(person);
		return modelMapper.map(person, PersonDto.class);
	}

	@Override
	public PersonDto deletePerson(Integer id) {
		Person person = personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
		personRepository.delete(person);
		return modelMapper.map(person, PersonDto.class);
	}

	private int calcAge(Person person) {
		return (int) ChronoUnit.YEARS.between(person.getBirthDate(), LocalDate.now());
	}

}
