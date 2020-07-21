package com.study.jpa.examples;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.study.jpa.domain.BaseEntity;

import lombok.Data;

@Entity
@Table(name = "t_example_person")
@Data
public class PersonExample extends BaseEntity{

	String firstname, lastname;
	@ManyToOne(cascade = CascadeType.PERSIST)
	Address address;

	@Entity
	@Table(name = "t_example_address")
	@Data
	static class Address {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		Long id;String zipCode, city, street;
	}
	
	public static class AddressBuilder{
		Address address;
		private AddressBuilder() {
			this.address=new Address();
		}
		public static AddressBuilder newBuilder() {
			return new AddressBuilder();
		}
		
		public AddressBuilder zipCode(String zipCode) {
			this.address.setZipCode(zipCode);
			return this;
		}
		
		public AddressBuilder city(String city) {
			this.address.setCity(city);
			return this;
		}
		
		public AddressBuilder street(String street) {
			this.address.setStreet(street);
			return this;
		}
		
		public 	Address build() {
			return this.address;
		}
	}
}
