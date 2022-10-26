package com.gdu.app01.xml03;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringMain {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("xml03/appCtx.xml");
		Person person = ctx.getBean("person", Person.class);

		System.out.println(person.getName());
		System.out.println(person.getAge());
		Address add = person.getAddr();
		System.out.println(add.getJibun());
		System.out.println(add.getRoad());
		System.out.println(add.getZipCode());
		
		ctx.close();
		
	}

}
