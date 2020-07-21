package com.study;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.study.jpa.projections.NamesOnly;
import com.study.jpa.projections.Person;
import com.study.service.ExampleService;
import com.study.service.ProjectionService;
import com.study.service.SpecsService;
import com.study.service.UserService;

public class JpaMain {
//InetAddress
	// ConcurrentHashMap
	private static class Blocker {}
	static Blocker blocker=new Blocker();

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(com.study.config.JpaConfiguration.class);
		try {
			context.refresh();

			// testTypedQuery(context);
//		testJpaProjection(context);
			// testJpaSpecifition(context);

		//	testJpaExample(context);
			testJpaExampleByNested(context);
		} finally {
			context.close();
		}

	}

	 static void testJpaExampleByNested(AnnotationConfigApplicationContext context) {
		 ExampleService service=context.getBean(ExampleService.class);
		 service.findByNestedProp("All", "Shanghai");
	}

	static void testJpaExample(AnnotationConfigApplicationContext context) {
		ExampleService service=context.getBean(ExampleService.class);
		for(;;) {
			String firstname="All-"+UUID.randomUUID();
			service.save(firstname);
			if(service.countByExample("All")>5) {
				break;
			}
			LockSupport.parkNanos(blocker, TimeUnit.SECONDS.toNanos(1));
		}
		service.queryByExample("All");//模糊查询不需要加通配符%
	}

	static void testJpaSpecifition(AnnotationConfigApplicationContext context) {
		SpecsService service = context.getBean(SpecsService.class);
//		service.batchSave(10);
		int years = 2;
		Object o = service.specQuery(years);
		System.out.println(o);
	}

	static void testJpaProjection(AnnotationConfigApplicationContext context) {
		ProjectionService service = context.getBean(ProjectionService.class);
		String firstName = "jiejie", lastName = "shangcj";
//		 Object o=service.save(firstName, lastName);
//		 System.out.println(o);

		Object o = service.onlyNames(lastName);
		System.out.println(o);

		// test dynamic projection
		o = service.dynamicProjections(lastName, Person.class);
		System.out.println(o);

		System.out.println(service.dynamicProjections(lastName, NamesOnly.class));
	}

	static void testTypedQuery(AnnotationConfigApplicationContext context) {
		UserService userService = context.getBean(UserService.class);
		System.out.println(userService.getClass());

		String userName = "allger2";
		Object o = userService.testSave(userName);
//		System.out.println(o);

		Object o1 = userService.typedQuery(userName);
		System.out.println("typed Query=" + o1);
	}
}
