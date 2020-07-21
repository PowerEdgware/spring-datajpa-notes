package com.study.service;

import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.study.jpa.examples.PersonExample;
import com.study.jpa.examples.PersonExample.AddressBuilder;
import com.study.jpa.examples.PersonExampleRepos;

import lombok.Value;

/**
 * 1.适应场景：动态属性设置和查询
 * 2.缺点：不支持内嵌或分组属性约束，仅支持字符串的包含/正则/以..开始/结尾等，仅支持其他属性的精确匹配
 * @author all
 *
 */
@Service
@Value
public class ExampleService {
	PersonExampleRepos personRepos;
	
	public PersonExample save(String firstname) {
		PersonExample person=new PersonExample();
		person.setFirstname(firstname);
		person.setLastname(UUID.randomUUID().toString());
		
		AddressBuilder builder=AddressBuilder.newBuilder();
		builder.city("Shanghai")
			.street("abc")
			.zipCode("021-2002");
		person.setAddress(builder.build());
		
		return personRepos.save(person);
	}
	//firstname for query
	public void queryByExample(String firstname) {
		//1.simple Example
		PersonExample person=new PersonExample();
		//Set the properties to query.
		person.setFirstname(firstname);
		Example<PersonExample> example=Example.of(person);
		System.out.println(personRepos.findAll(example));
		
		//2. Example Matchers
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withIgnorePaths("lastname")// ignore the lastname property path.
				//.withIncludeNullValues()
				// perform prefix string matching
				//不需要加通配符
				.withStringMatcher(StringMatcher.STARTING);
		 example=Example.of(person,matcher);//based on the domain object and the configured ExampleMatcher
		 
		 Pageable pageable= PageRequest.of(0,10,Sort.unsorted());
		 System.out.println(personRepos.findAll(example, pageable).getContent());
		 
	}
	
	public long countByExample(String firstname) {
		PersonExample probe=new PersonExample();
		probe.setFirstname(firstname);
		
		ExampleMatcher matcher = ExampleMatcher.matching()
//				  .withMatcher("firstname", match -> match.endsWith())
				  .withMatcher("firstname", match -> match.startsWith());
		
		Example<PersonExample> example=Example.of(probe, matcher);
		return  personRepos.count(example);
	}
	
	public void findByNestedProp(String firstname,String city) {
		PersonExample probe=new PersonExample();
		probe.setFirstname(firstname);
		probe.setAddress(AddressBuilder.newBuilder()
		.city(city).build());
		
		ExampleMatcher matcher = ExampleMatcher.matching()
				  .withMatcher("address.city", match -> match.stringMatcher(StringMatcher.EXACT))
				  .withMatcher("firstname", match -> match.startsWith());
		
		
		Example<PersonExample> example=Example.of(probe, matcher);
		
		 Pageable pageable= PageRequest.of(0,10,Sort.unsorted());
		 System.out.println(personRepos.findAll(example, pageable).getContent());
	}
}
