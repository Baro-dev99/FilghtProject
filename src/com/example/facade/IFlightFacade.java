package com.example.facade;

import java.util.List;

import com.example.exceptions.DAOException;
import com.example.exceptions.InvalidFlightException;
import com.example.exceptions.InvalidTokenException;
import com.example.models.Flight;

public interface IFlightFacade {

	public void addFlight(Flight flight, String token) throws InvalidTokenException, InvalidFlightException, DAOException;

	public void removeFlight(String id, String token) throws InvalidTokenException, InvalidFlightException, DAOException;

	public void updateFlight(String id, Flight newFlight, String token) throws InvalidTokenException, InvalidFlightException, DAOException;

	public Flight findFlight(String id, String token) throws InvalidTokenException, InvalidFlightException, DAOException;

	public List<Flight> findAllFlights(String token) throws InvalidTokenException, DAOException;
}
