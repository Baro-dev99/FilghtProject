package com.example.manager.impl;

import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.exceptions.DAOException;
import com.example.exceptions.InvalidFlightException;
import com.example.manager.IFlightManager;
import com.example.models.Flight;
import com.example.service.IFlightService;
import com.example.utilities.MyLogger;

@Component
public class FlightManager implements IFlightManager {
	
	private IFlightService service;

	@Autowired
	public FlightManager(@Qualifier("flightService") IFlightService service) {
		this.service = service;
	}

	@Override
	public void addFlight(Flight flight) throws InvalidFlightException, DAOException {
		String msg;

		try {
			service.addFlight(flight);
			msg = "Flight[" + flight.getId() + "] saved successfully!";
			MyLogger.log(Level.INFO, msg);

		} catch (InvalidFlightException e) {
			msg = "Flight[" + flight.getId() + "] not saved: " + e.getMessage();
			MyLogger.log(Level.WARNING, msg);
			throw new InvalidFlightException(msg);
		} catch (DAOException e) {
			MyLogger.log(Level.WARNING, e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void removeFlight(String id) throws InvalidFlightException, DAOException {
		String msg;
		
		try {
			service.removeFlight(id);
			msg = "Flight[" + id + "] removed successfully!";
			MyLogger.log(Level.INFO, msg);
			
		} catch (InvalidFlightException e) {
			msg = "Flight[" + id + "] not removed: " + e.getMessage();
			MyLogger.log(Level.WARNING, e.getMessage());
			throw new InvalidFlightException(msg);
		} catch (DAOException e) {
			MyLogger.log(Level.WARNING, e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public void updateFlight(String id, Flight newFlight) throws InvalidFlightException, DAOException {
		String msg;

		try {
			service.updateFlight(id, newFlight);
			msg = "Flight[" + id + "] updated successfully!";
			MyLogger.log(Level.INFO, msg);

		} catch (InvalidFlightException e) {
			msg = "Flight[" + id + "] not updated: " + e.getMessage();
			MyLogger.log(Level.WARNING, msg);
			throw new InvalidFlightException(msg);
		} catch (DAOException e) {
			MyLogger.log(Level.WARNING, e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

	@Override
	public Flight findFlight(String id) throws InvalidFlightException, DAOException {
		try {
			return service.findFlight(id);
		} catch (InvalidFlightException e) {
			MyLogger.log(Level.WARNING, e.getMessage());
			throw new InvalidFlightException(e.getMessage());
		} catch (DAOException e) {
			MyLogger.log(Level.WARNING, e.getMessage());
			throw new DAOException(e.getMessage());
		}

	}

	@Override
	public List<Flight> findAllFlights() throws DAOException {
		try {
			return service.findAllFlights();
		} catch (DAOException e) {
			MyLogger.log(Level.WARNING, e.getMessage());
			throw new DAOException(e.getMessage());
		}
	}

}
