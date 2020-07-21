package com.study.jdbc;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonService {

	final PersonRepos personRepos;
	
	public Person queryPerson(long id) {
		return personRepos.findById(id).orElseThrow(RuntimeException::new);
	}
	
	public List<Person> queryPerson(String firstName) {
		return personRepos.findByFirstname(firstName);
	}
	@Transactional
	public Person save(String firstname, String lastname, LocalDate birthday) {
		Person person=Person.of(firstname, lastname, birthday,"","");
		System.out.println("Before save:"+person);
		person=personRepos.save(person);
		
		System.out.println("After save:"+person);
		return person;
	}
}
