package com.study.jdbc;

import java.time.LocalDate;
import java.time.Period;


import org.springframework.data.annotation.AccessType;
import org.springframework.data.annotation.AccessType.Type;
import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//@Table(name="t_person")
@org.springframework.data.relational.core.mapping.Table(value = "t_person")
@ToString
@Setter
@Getter
public class Person {
	private  @Id Long id;
	private  String firstname, lastname;
	private  LocalDate birthday;
	private  int age;

	private String comment;
	private @AccessType(Type.PROPERTY) String remarks;//set/get method must exists

	static Person of(String firstname, String lastname, LocalDate birthday,String remarks,String comment) {
		return new Person(null, firstname, lastname, birthday, Period.between(birthday, LocalDate.now()).getYears(),remarks,comment);
	}
	
	public Person() {
		//this(null, null, null, null, 0, null, null);
	}

	 Person(Long id, String firstname, String lastname, LocalDate birthday, int age,String remarks,String comment) {
		this.comment = comment;
		this.remarks = remarks;
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthday = birthday;
		this.age = age;
	}

	Person withId(Long id) {//Used for Create
		return new Person(id, this.firstname, this.lastname, this.birthday, this.age,this.remarks,this.comment);
	}

//	public void setRemarks(String remarks) {
//		this.remarks = remarks;
//	}
	
	public String getRemarks() {
		return this.remarks;
	}
	
	public Long getId() {
		return this.id;
	}
}
