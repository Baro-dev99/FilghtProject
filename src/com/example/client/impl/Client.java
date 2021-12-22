package com.example.client.impl;

import com.example.dao.impl.AirlineDAO;
import com.example.dao.impl.AirportDAO;
import com.example.dao.impl.FlightDAO;
import com.example.exceptions.DAOException;
import com.example.exceptions.InvalidFlightException;
import com.example.exceptions.InvalidTokenException;
import com.example.facade.IFlightFacade;
import com.example.facade.impl.FlightFacade;
import com.example.manager.impl.FlightManager;
import com.example.models.Flight;
import com.example.models.Flight.FlightType;
import com.example.service.impl.FlightService;

public class Client {
	
	private final String token = "112233";
	private IFlightFacade facade;

	public Client(IFlightFacade facade) {
		this.facade = facade;
	}

	public IFlightFacade getFacade() {
		return facade;
	}

	public String getToken() {
		return token;
	}

	public static void main(String[] args) {
		
		/** Creating a client **/
		Client client = new Client(
				new FlightFacade(
						new FlightManager(
								new FlightService(
										new FlightDAO(),
										new AirportDAO(),
										new AirlineDAO()))));

		/** Creating flights **/
		Flight f1 = new Flight();
		f1.setAirlineId("al1001")
				.setDestAirportId("a1002")
				.setSrcAirportId("a1000")
				.setSobt("13:35")
				.setSibt("00:00")
				.setType(FlightType.arrival)
				.setId("f1006")
				.setCreatedBy("user1")
				.setDateCreated("monday")
				.setModifiedBy("user1")
				.setDateModified("monday");
		
		Flight f2 = new Flight();
		f2.setAirlineId("al1000")
				.setDestAirportId("a1000")
				.setSrcAirportId("a1002")
				.setSobt("02:55")
				.setSibt("18:30")
				.setType(FlightType.departure)
				.setId("f1006")
				.setCreatedBy("user1")
				.setDateCreated("monday")
				.setModifiedBy("user1")
				.setDateModified("monday");
		
		/** Testing addFlight service **/
		try {
			client.getFacade().addFlight(f1, client.getToken());
			System.out.println("Flight saved successfully!");
		} catch (InvalidTokenException | InvalidFlightException | DAOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("*************************");
		
		/** Testing findAllFlights service **/
		try {
			client.getFacade().findAllFlights(client.getToken()).forEach(System.out::println);;
		} catch (InvalidTokenException | DAOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("*************************");
		
		/** Testing updateFlight service **/
		try {
			client.getFacade().updateFlight("f1006", f2, client.getToken());
			System.out.println("Flight updated successfully");
		} catch (InvalidTokenException | DAOException | InvalidFlightException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("*************************");
		
		/** Testing removeFlight service **/
		try {
			client.getFacade().removeFlight("f1006", client.getToken());
			System.out.println("Flight removed successfully");
		} catch (InvalidTokenException | DAOException | InvalidFlightException e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		System.out.println("*************************");

		/** Testing findFlight service **/
		try {
			Flight f11 = client.getFacade().findFlight("f1006", client.getToken());
			System.out.println(f11);
		} catch (InvalidTokenException | InvalidFlightException | DAOException e) {
			System.out.println("Error: " + e.getMessage());
		}
	}

}
