package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.dao.IAirlineDAO;
import com.example.dao.IAirportDAO;
import com.example.dao.IFlightDAO;
import com.example.exceptions.DAOException;
import com.example.exceptions.InvalidFlightException;
import com.example.models.Flight;
import com.example.service.IFlightService;

@Component
public class FlightService implements IFlightService {

//	private IDAO<?> dao;
//
//	private void setToFlightDAO() {
//		dao = DAOFactory.createNewFlightDAO();
//	}
//
//	private void setToAirlineDAO() {
//		dao = DAOFactory.createNewAirlineDAO();
//	}
//
//	private void setToAirportDAO() {
//		dao = DAOFactory.createNewAirportDAO();
//	}

	private IFlightDAO fdao;
	private IAirportDAO adao;
	private IAirlineDAO aldao;

	@Autowired
	public FlightService(@Qualifier("flightDAO") IFlightDAO fdao, @Qualifier("airportDAO") IAirportDAO adao,
			@Qualifier("airlineDAO") IAirlineDAO aldao) {
		this.fdao = fdao;
		this.adao = adao;
		this.aldao = aldao;
	}

	public boolean airportExists(String id) {

		List<String> airportsIds;

		try {
			airportsIds = adao.getAll()
					.stream()
					.map(a -> a.getId())
					.collect(Collectors.toList());

			if (airportsIds == null)
				return false;
			return airportsIds.contains(id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean airlineExists(String id) {
		List<String> airlinesIds;

		try {
			airlinesIds = aldao.getAll()
					.stream()
					.map(a -> a.getId())
					.collect(Collectors.toList());

			if (airlinesIds == null)
				return false;
			return airlinesIds.contains(id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	public boolean flightExists(String id) {
		List<String> flightsIds;

		try {
			flightsIds = fdao.getAll()
					.stream()
					.map(f -> f.getId())
					.collect(Collectors.toList());

			if (flightsIds == null)
				return false;
			return flightsIds.contains(id);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void addFlight(Flight flight) throws InvalidFlightException, DAOException {
		if (flightExists(flight.getId())) {
			throw new InvalidFlightException("Flight already exists!");
		}

		if (!airportExists(flight.getSrcAirportId())) {
			throw new InvalidFlightException("Source Airport[" + flight.getSrcAirportId() + "] does not exist!");
		}

		if (!airportExists(flight.getDestAirportId())) {
			throw new InvalidFlightException("Destination Airport[" + flight.getDestAirportId() + "] does not exist!");
		}

		if (!airlineExists(flight.getAirlineId())) {
			throw new InvalidFlightException("Airline[" + flight.getAirlineId() + "] does not exist!");
		}

//		setToFlightDAO();

		fdao.add(flight);
	}

	@Override
	public void removeFlight(String id) throws InvalidFlightException, DAOException {
		if (!flightExists(id)) {
			throw new InvalidFlightException("Flight does not exist");
		}

		fdao.remove(id);
	}

	@Override
	public void updateFlight(String id, Flight newFlight) throws InvalidFlightException, DAOException {
		if (!flightExists(id)) {
			throw new InvalidFlightException("Flight does not exist!");
		}

		if (!airportExists(newFlight.getSrcAirportId())) {
			throw new InvalidFlightException("Source Airport[" + newFlight.getSrcAirportId() + "] does not exist!");
		}

		if (!airportExists(newFlight.getDestAirportId())) {
			throw new InvalidFlightException(
					"Destination Airport[" + newFlight.getDestAirportId() + "] does not exist!");
		}

		if (!airlineExists(newFlight.getAirlineId())) {
			throw new InvalidFlightException("Airline[" + newFlight.getAirlineId() + "] does not exist!");
		}

		fdao.update(id, newFlight);
	}

	@Override
	public Flight findFlight(String id) throws InvalidFlightException, DAOException {
		if (!flightExists(id)) {
			throw new InvalidFlightException("Flight[" + id + "] not found!");
		}

		return fdao.getById(id);
	}

	@Override
	public List<Flight> findAllFlights() throws DAOException {
		return fdao.getAll();
	}

	// Service Test
//	public static void main(String[] args) throws DAOException {
//
//		Flight f1 = new Flight();
//		f1.setAirlineId("al1001")
//				.setDestAirportId("a1000")
//				.setSrcAirportId("a1002")
//				.setSobt("05:55")
//				.setSibt("12:30")
//				.setType(FlightType.arrival)
//				.setId("f1005")
//				.setCreatedBy("user1")
//				.setDateCreated("monday")
//				.setModifiedBy("user1")
//				.setDateModified("monday");
//
//		IFlightService service = new FlightServiceImpl(new FlightDAO(), new AirportDAO(), new AirlineDAO());
//		try {
//			service.addFlight(f1);
//		} catch (InvalidFlightException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
