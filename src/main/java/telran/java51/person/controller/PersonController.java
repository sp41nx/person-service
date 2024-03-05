package telran.java51.person.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import telran.java51.person.dto.PersonDto;
import telran.java51.person.dto.PopulationDto;
import telran.java51.person.model.Address;
import telran.java51.person.service.PersonService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {
	
	final PersonService personService;
    
	@PostMapping
	public Boolean addPerson(@RequestBody PersonDto personDto) {
		return personService.addPerson(personDto);
	}
	
	@GetMapping("/{id}")
	public PersonDto findPersonById(@PathVariable Integer id) {
		return personService.findPersonById(id);
	}
	
	@GetMapping("/city/{city}")
	public Iterable<PersonDto> findPersonsByCity(@PathVariable String city) {
		return personService.findPersonsByCity(city);
	}
	
	@GetMapping("/ages/{from}/{to}")
	public Iterable<PersonDto> findPersonsBetweenAges(@PathVariable Integer from, @PathVariable Integer to) {
		return personService.findPersonsBetweenAges(from, to);
	}
	
	@PutMapping("/{id}/name/{name}")
	public PersonDto updatePersonName(@PathVariable Integer id, @PathVariable String name) {
		return personService.updatePersonName(id, name);
	}
	
	@GetMapping("/name/{name}")
	public List<PersonDto> findPersonsByName(@PathVariable String name) {
		return personService.findPersonsByName(name);
	}
	
	@GetMapping("/population/city")
	public Iterable<PopulationDto> getCityPopulation() {
		return personService.getCityPopulation();
	}
	
	@PutMapping("/{id}/address")
	public PersonDto updatePersonAddress(@PathVariable Integer id, @RequestBody Address address) {
		return personService.updatePersonAddress(id, address);
	}
	
	@DeleteMapping("{id}")
	public PersonDto deletePerson(@PathVariable Integer id) {
		return personService.deletePerson(id);
	}
	
}
