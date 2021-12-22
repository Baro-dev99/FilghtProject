package com.example.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.exceptions.DAOException;
import com.example.exceptions.InvalidFlightException;
import com.example.exceptions.InvalidTokenException;
import com.example.facade.AbstractFacade;
import com.example.facade.IFlightFacade;
import com.example.manager.IFlightManager;
import com.example.models.Flight;

@Component
public class FlightFacade extends AbstractFacade implements IFlightFacade {

	private IFlightManager manager;

	@Autowired
	public FlightFacade(@Qualifier("flightManager") IFlightManager manager) {
		this.manager = manager;
	}
	
	@Override
	public void addFlight(Flight flight, String token) throws InvalidTokenException, InvalidFlightException, DAOException {
		if (!super.validateToken(token)) {
			throw new InvalidTokenException("Invalid Token!");
		}

		manager.addFlight(flight);
	}

	@Override
	public void removeFlight(String id, String token) throws InvalidTokenException, InvalidFlightException, DAOException {
		if (!super.validateToken(token)) {
			throw new InvalidTokenException("Invalid Token!");
		}
		
		manager.removeFlight(id);
	}

	@Override
	public void updateFlight(String id, Flight newFlight, String token) throws InvalidTokenException, InvalidFlightException, DAOException {
		if (!super.validateToken(token)) {
			throw new InvalidTokenException("Invalid Token!");
		}
		
		manager.updateFlight(id, newFlight);
	}

	@Override
	public Flight findFlight(String id, String token) throws InvalidTokenException, InvalidFlightException, DAOException {
		if (!super.validateToken(token)) {
			throw new InvalidTokenException("Invalid Token!");
		}
		
		return manager.findFlight(id);
	}

	@Override
	public List<Flight> findAllFlights(String token) throws InvalidTokenException, DAOException {
		if (!super.validateToken(token)) {
			throw new InvalidTokenException("Invalid Token!");
		}
		
		return manager.findAllFlights();
	}

}
