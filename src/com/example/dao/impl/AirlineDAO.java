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

import com.example.dao.IAirlineDAO;
import com.example.exceptions.DAOException;
import com.example.models.Airline;

@Component
public class AirlineDAO implements IAirlineDAO {
	private final File srcFile = new File("data/airlines.dat");

	public AirlineDAO() {
	}

	@Override
	public Airline getById(String id) throws DAOException {
		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(srcFile)))) {
			boolean eof = false;
			while (!eof) {
				try {
					Airline airline = (Airline) in.readObject();
					if (airline.getId().equals(id)) {
						return airline;
					}
				} catch (EOFException | ClassNotFoundException e) {
					eof = true;
				}
			}
		} catch (IOException e1) {
			throw new DAOException("Could not load Airline");
		}

		return null;
	}

	@Override
	public List<Airline> getAll() throws DAOException {

		List<Airline> airlines = new ArrayList<>();

		try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(srcFile)))) {
			boolean eof = false;
			while (!eof) {
				try {
					Airline airline = (Airline) in.readObject();
					airlines.add(airline);
				} catch (IOException | ClassNotFoundException e) {
					eof = true;
				}
			}
			return airlines;
		} catch (IOException e1) {
			throw new DAOException("Could not load Airlines");
		}
	}

	@Override
	public void add(Airline airline) throws DAOException {
		List<Airline> airlines;
		try {
			airlines = getAll();
			airlines.add(airline);
			addAll(airlines);

		} catch (DAOException e) {
			throw new DAOException("Could not add Airline");
		}
	}

	@Override
	public void remove(String id) throws DAOException {
		List<Airline> airlines;
		try {
			airlines = getAll().stream()
					.filter(f -> !f.getId().equals(id))
					.collect(Collectors.toList());

			addAll(airlines);
		} catch (DAOException e) {
			throw new DAOException("Could not remove Airline");
		}

	}

	@Override
	public void update(String id, Airline airline) throws DAOException {
		List<Airline> airlines;
		try {
			airlines = getAll().stream()
					.map(f -> f.getId().equals(id) ? airline : f)
					.collect(Collectors.toList());

			addAll(airlines);
		} catch (DAOException e) {
			throw new DAOException("Could not update Airline");
		}

	}

	private void addAll(List<Airline> airlines) {
		try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(srcFile)))) {
			for (Airline airline : airlines) {
				out.writeObject(airline);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// DAO Test
//	public static void main(String[] args) throws DAOException {
//		Airline a1 = new Airline();
//		a1.setRating(8.5)
//				.setName("Middle East")
//				.setId("al1000")
//				.setCreatedBy("user1")
//				.setDateCreated("monday")
//				.setModifiedBy("user1")
//				.setDateModified("monday");
//
//		Airline a2 = new Airline();
//		a2.setRating(7.7)
//				.setName("American Airlines")
//				.setId("al1001")
//				.setCreatedBy("user1")
//				.setDateCreated("monday")
//				.setModifiedBy("user1")
//				.setDateModified("monday");
//
//		Airline a3 = new Airline();
//		a3.setRating(6.9)
//				.setName("European Airlines")
//				.setId("al1002")
//				.setCreatedBy("user1")
//				.setDateCreated("monday")
//				.setModifiedBy("user1")
//				.setDateModified("monday");
//
//		addAll(List.of(a1, a2, a3));
//		
//		AirlineDAO dao = new AirlineDAO();
//		dao.add(a1);
//		dao.add(a2);
//		dao.add(a3);
//		
//		dao.getAll().forEach(System.out::println);
//		
//	}
}
