package com.example.dao.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.example.dao.IFlightDAO;
import com.example.exceptions.DAOException;
import com.example.models.Flight;

@Component
public class FlightDAO implements IFlightDAO {

	private final File srcFile = new File("data/flights.dat");

	@Override
	public Flight getById(String id) throws DAOException {
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(srcFile)))) {
			boolean eof = false;
			while (!eof) {
				try {
					Flight flight = (Flight) in.readObject();
					if (flight.getId().equals(id)) {
						return flight;
					}
				} catch (EOFException | ClassNotFoundException e) {
					eof = true;
				}
			}
		} catch (IOException e1) {
			throw new DAOException("Could not load Flight");
		}

		return null;
	}

	@Override
	public List<Flight> getAll() throws DAOException {

		List<Flight> flights = new ArrayList<>();

		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(srcFile)))) {
			boolean eof = false;
			while (!eof) {
				try {
					Flight flight = (Flight) in.readObject();
					flights.add(flight);
				} catch (IOException | ClassNotFoundException e) {
					eof = true;
				}
			}
			return flights;
		} catch (IOException e1) {
			throw new DAOException("Could not load Flights");
		}
	}

	@Override
	public void add(Flight flight) throws DAOException {
		List<Flight> flights;
		try {
			flights = getAll();
			flights.add(flight);
			addAll(flights);

		} catch (DAOException e) {
			throw new DAOException("Could not add Flight");
		}
	}

	@Override
	public void remove(String id) throws DAOException {
		List<Flight> flights;
		try {
			flights = getAll().stream()
					.filter(f -> !f.getId().equals(id))
					.collect(Collectors.toList());

			addAll(flights);
		} catch (DAOException e) {
			throw new DAOException("Could not remove Flight");
		}

	}

	@Override
	public void update(String id, Flight flight) throws DAOException {
		List<Flight> flights;
		try {
			flights = getAll().stream()
					.map(f -> f.getId().equals(id) ? flight : f)
					.collect(Collectors.toList());

			addAll(flights);
		} catch (DAOException e) {
			throw new DAOException("Could not update Flight");
		}

	}

	private void addAll(List<Flight> flights) {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(srcFile)))) {
			for (Flight flight : flights) {
				out.writeObject(flight);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// DAO Test
//	public static void main(String[] args) throws DAOException {
//		FlightDAO dao = new FlightDAO();
//
//		Flight f1 = new Flight();
//		f1.setAirlineId("al1002")
//				.setDestAirportId("a1000")
//				.setSrcAirportId("a1001")
//				.setSobt("14:55")
//				.setSibt("22:30")
//				.setType(FlightType.departure)
//				.setId("f1000")
//				.setCreatedBy("user1")
//				.setDateCreated("monday")
//				.setModifiedBy("user1")
//				.setDateModified("monday");
//
//		Flight f2 = new Flight();
//		f2.setAirlineId("al1000")
//				.setDestAirportId("a1001")
//				.setSrcAirportId("a1002")
//				.setSobt("22:55")
//				.setSibt("02:00")
//				.setType(FlightType.arrival)
//				.setId("f1001")
//				.setCreatedBy("user1")
//				.setDateCreated("wednesday")
//				.setModifiedBy("user2")
//				.setDateModified("thursday");
//
//		Flight f3 = new Flight();
//		f3.setAirlineId("al1002")
//				.setDestAirportId("a1002")
//				.setSrcAirportId("a1001")
//				.setSobt("18:10")
//				.setSibt("09:45")
//				.setType(FlightType.arrival)
//				.setId("f1002")
//				.setCreatedBy("user1")
//				.setDateCreated("tuesday")
//				.setModifiedBy("user1")
//				.setDateModified("tuesday");
//
//		Flight f4 = new Flight();
//		f4.setAirlineId("al1001")
//				.setDestAirportId("a1000")
//				.setSrcAirportId("a1002")
//				.setSibt("22:55")
//				.setSobt("02:00")
//				.setType(FlightType.departure)
//				.setId("f1003")
//				.setCreatedBy("user2")
//				.setDateCreated("monday")
//				.setModifiedBy("user2")
//				.setDateModified("thursday");

//		addAll(List.of(f1, f2, f3, f4));

//		dao.update("f1002", f2);
		
//		dao.getAll().forEach(System.out::println);
//	}

}
