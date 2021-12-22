package com.example.models;

import java.io.Serializable;
import java.util.Objects;

public abstract class MasterData extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String name;

	public MasterData() {
	}

	public MasterData(String id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public MasterData setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterData other = (MasterData) obj;
		return Objects.equals(id, other.id);
	}

}
