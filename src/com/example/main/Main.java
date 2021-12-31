package com.example.main;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.client.impl.IClient;
import com.example.exceptions.DAOException;
import com.example.exceptions.InvalidTokenException;

public class Main {

	public static void main(String[] args) {
		
//		try (ClassPathXmlApplicationContext context = 
//				new ClassPathXmlApplicationContext("applicationContext.xml");) {
		
		try (AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(FlightConfig.class);) {
		
			IClient client = context.getBean("clientSpring", IClient.class);

			client.getFlightFacade().findAllFlights("112233").forEach(System.out::println);

		} catch (InvalidTokenException | DAOException e) {
			System.out.println(e);
		}

	}
}
