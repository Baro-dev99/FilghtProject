package com.example.models;

import java.io.Serializable;

public class Airport extends MasterData implements Serializable {

	private static final long serialVersionUID = 1L;

	private String location;
	private long capacity;

	public Airport(String id, String name, String location, long capacity) {
		super(id, name);
		this.location = location;
		this.capacity = capacity;
	}

	public Airport() {
	}

	public String getLocation() {
		return location;
	}

	public Airport setLocation(String location) {
		this.location = location;
		return this;
	}

	public long getCapacity() {
		return capacity;
	}

	public Airport setCapacity(long capacity) {
		this.capacity = capacity;
		return this;
	}

	@Override
	public String toString() {
		return "Airport [id=" + id + ", name=" + name + ", location=" + location + ", capacity=" + capacity + "]";
	}

}
