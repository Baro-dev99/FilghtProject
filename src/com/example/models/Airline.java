package com.example.models;

import java.io.Serializable;

public class Airline extends MasterData implements Serializable {

	private static final long serialVersionUID = 1L;

	private double rating;

	public Airline(String id, String name, double rating) {
		super(id, name);
		this.rating = rating;
	}

	public Airline() {
	}

	public double getRating() {
		return rating;
	}

	public Airline setRating(double rating) {
		this.rating = rating;
		return this;
	}

	@Override
	public String toString() {
		return "Airline [id=" + id + ", name=" + name + ", rating=" + rating + "]";
	}

}
