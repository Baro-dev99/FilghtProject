package com.example.manager;

import java.util.List;

import com.example.exceptions.DAOException;
import com.example.exceptions.InvalidFlightException;
import com.example.models.Flight;

public interface IFlightManager {

	public void addFlight(Flight flight) throws InvalidFlightException, DAOException;

	public void removeFlight(String id) throws InvalidFlightException, DAOException;

	public void updateFlight(String id, Flight newFlight) throws InvalidFlightException, DAOException;

	public Flight findFlight(String id) throws InvalidFlightException, DAOException;

	public List<Flight> findAllFlights() throws DAOException;

}
