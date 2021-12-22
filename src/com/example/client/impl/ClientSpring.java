package com.example.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.example.facade.IFlightFacade;

@Component
public class ClientSpring implements IClient{

	private IFlightFacade flightFacade;

	@Autowired
	public ClientSpring(@Qualifier("flightFacade") IFlightFacade flightFacade) {
		this.flightFacade = flightFacade;
	}

	@Override
	public IFlightFacade getFlightFacade() {
		return flightFacade;
	}

}
