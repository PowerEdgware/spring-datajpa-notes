package com.study.jpa.projections;

import java.util.Collection;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepos extends JpaRepository<Person, UUID>{

	//Class-based Projections (DTOs)
	 Collection<NamesOnly> findByLastname(String lastname);
	 
	 //Dynamic Projections
	 <T> Collection<T> findByLastname(String lastname, Class<T> type);
}
