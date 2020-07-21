package com.study.jpa.projections;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "t_projection_person")
@Data
public class Person {

	@Id
	UUID id=UUID.randomUUID();
	String firstname, lastname;
	@ManyToOne
	Address address;

	@Entity
	@Table(name = "t_projection_address")
	@Data
	static class Address {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Long id;String zipCode, city, street;
	}
}
