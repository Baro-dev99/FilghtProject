package com.example.dao.impl;

import com.example.dao.IDAO;
import com.example.exceptions.DAOException;

public class DAOFactory {
	public enum DAOType {
		flightDAO,
		airportDAO,
		airlineDAO
	}

	public static IDAO<?> createDAO(DAOType type) {

		switch (type) {
		case flightDAO:
			return new FlightDAO();

		case airportDAO:
			return new AirportDAO();

		case airlineDAO:
			return new AirlineDAO();

		default:
			return null;
		}
	}

	public static IDAO<?> createNewFlightDAO() {
		return new FlightDAO();
	}

	public static IDAO<?> createNewAirlineDAO() {
		return new AirlineDAO();
	}

	public static IDAO<?> createNewAirportDAO() {
		return new AirportDAO();
	}

	public static void main(String[] args) throws DAOException {
//		IDAO<?> dao = DAOFactory.createDAO(DAOType.flightDAO);
		IDAO<?> dao = DAOFactory.createNewFlightDAO();

//		Flight f = (Flight) dao.getById("f1002");
//		System.out.println(f);

		dao.getAll().forEach(System.out::println);
	}
}
