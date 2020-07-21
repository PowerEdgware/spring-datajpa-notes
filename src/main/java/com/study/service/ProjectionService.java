package com.study.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.study.jpa.projections.NamesOnly;
import com.study.jpa.projections.Person;
import com.study.jpa.projections.PersonRepos;

import lombok.Value;

@Service
@Value
public class ProjectionService {

	PersonRepos personRepos;
	
	public Collection<NamesOnly> onlyNames(String lastName){
		return personRepos.findByLastname(lastName);
	}
	
	public Person save(String firstName ,String lastName) {
		Person p=new Person();
		p.setFirstname(firstName);
		p.setLastname(lastName);
		
		return personRepos.save(p);
	}
	
	public <E> Collection<E> dynamicProjections(String lastName,Class<E> type){
		return  personRepos.findByLastname(lastName, type);
	}
}
