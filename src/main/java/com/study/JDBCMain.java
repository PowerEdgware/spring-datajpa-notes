package com.study;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.jdbc.repository.QueryMappingConfiguration;

import com.study.jdbc.Person;
//import org.springframework.data.jdbc.repository.config.JdbcConfiguration;
import com.study.jdbc.PersonService;
import com.study.jdbc.UserService;
import com.study.jdbc.embbed.CustomUser;

public class JDBCMain {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(com.study.config.JdbcConfiguration.class);

		context.refresh();

		// testPerson(context);
		
		Object o=context.getBean(QueryMappingConfiguration.class);
		System.err.println(o.getClass());

		testUser(context);

		context.close();
		// NamingStrategy
	}

	static void testUser(AnnotationConfigApplicationContext context) {
		UserService userService=context.getBean(UserService.class);
		String phoneName="huawei";
		Random rnd=new Random();
		int rndNum=rnd.nextInt(100);
		String userName="abcx"+rndNum;
		String phoneNum="021-2983003"+rndNum;
		CustomUser user=userService.save(userName, phoneName, phoneNum);
		System.out.println(user);
		
		Long userId=3L;
		user=userService.findOne(userId);
		System.out.println("Found="+user);
		
		System.out.println("Findby UserName");
		user=userService.findByUserName(userName);
		System.out.println("Name Query="+user);
	}

	static void testPerson(AnnotationConfigApplicationContext context) {
		PersonService personService = context.getBean(PersonService.class);

		String firstname = "test1";
		String lastname = "shangcj";
		LocalDate birthday = LocalDate.of(1988, 2, 24);
		Person saved = personService.save(firstname, lastname, birthday);

		List<Person> persons = personService.queryPerson(firstname);
		System.out.println("Found:" + persons);
	}
}
