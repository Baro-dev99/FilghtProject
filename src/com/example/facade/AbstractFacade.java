package com.example.facade;

public abstract class AbstractFacade {
	private final String TOKEN = "112233";
	
	public boolean validateToken(String token) {
		return token.equals(TOKEN) ? true : false;
	}
}
