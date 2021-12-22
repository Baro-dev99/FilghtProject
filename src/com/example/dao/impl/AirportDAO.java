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

import com.example.dao.IAirportDAO;
import com.example.exceptions.DAOException;
import com.example.models.Airport;

@Component
public class AirportDAO implements IAirportDAO {
	private static final File srcFile = new File("data/airports.dat");

	public AirportDAO() {
	}

	@Override
	public Airport getById(String id) throws DAOException {
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(srcFile)))) {
			boolean eof = false;
			while (!eof) {
				try {
					Airport airport = (Airport) in.readObject();
					if (airport.getId().equals(id)) {
						return airport;
					}
				} catch (EOFException | ClassNotFoundException e) {
					eof = true;
				}
			}
		} catch (IOException e1) {
			throw new DAOException("Could not load Airport");
		}

		return null;
	}

	@Override
	public List<Airport> getAll() throws DAOException {

		List<Airport> airports = new ArrayList<>();

		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(srcFile)))) {
			boolean eof = false;
			while (!eof) {
				try {
					Airport airport = (Airport) in.readObject();
					airports.add(airport);
				} catch (IOException | ClassNotFoundException e) {
					eof = true;
				}
			}
			return airports;
		} catch (IOException e1) {
			throw new DAOException("Could not load Airports");
		}
	}

	@Override
	public void add(Airport airport) throws DAOException {
		List<Airport> airports;
		try {
			airports = getAll();
			airports.add(airport);
			addAll(airports);

		} catch (DAOException e) {
			throw new DAOException("Could not add Airport");
		}

	}

	@Override
	public void remove(String id) throws DAOException {
		List<Airport> airports;
		try {
			airports = getAll().stream()
					.filter(f -> !f.getId().equals(id))
					.collect(Collectors.toList());

			addAll(airports);
		} catch (DAOException e) {
			throw new DAOException("Could not remove Airport");
		}

	}

	@Override
	public void update(String id, Airport airport) throws DAOException {
		List<Airport> airports;
		try {
			airports = getAll().stream()
					.map(f -> f.getId().equals(id) ? airport : f)
					.collect(Collectors.toList());

			addAll(airports);
		} catch (DAOException e) {
			throw new DAOException("Could not update Airport");
		}

	}

	private static void addAll(List<Airport> airports) {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(srcFile)))) {
			for (Airport airport : airports) {
				out.writeObject(airport);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// DAO Test
//	public static void main(String[] args) throws DAOException {
//		Airport a1 = new Airport();
//		a1.setCapacity(30)
//				.setLocation("France")
//				.setName("Paris Airport")
//				.setId("a1000")
//				.setCreatedBy("user1")
//				.setDateCreated("monday")
//				.setModifiedBy("user1")
//				.setDateModified("monday");
//
//		Airport a2 = new Airport();
//		a2.setCapacity(50)
//				.setLocation("UAE")
//				.setName("Dubai National Airport")
//				.setId("a1001")
//				.setCreatedBy("user1")
//				.setDateCreated("monday")
//				.setModifiedBy("user1")
//				.setDateModified("monday");
//
//		Airport a3 = new Airport();
//		a3.setCapacity(15)
//				.setLocation("Lebanon")
//				.setName("Rafic Hariri Airport")
//				.setId("a1002")
//				.setCreatedBy("user1")
//				.setDateCreated("monday")
//				.setModifiedBy("user1")
//				.setDateModified("monday");
//
//		AirportDAO dao = new AirportDAO();
//		
//		dao.add(a1);
//		dao.add(a2);
//		dao.add(a3);
//		
//		addAll(List.of(a1,a2,a3));
//		
//		dao.getAll().forEach(System.out::println);

//	}
}
