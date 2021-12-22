package com.example.models;

import java.io.Serializable;

public abstract class BaseModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected String id;
	protected String dateCreated;
	protected String dateModified;
	protected String createdBy;
	protected String modifiedBy;

	public BaseModel() {
	}

	public BaseModel(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}

	public BaseModel setId(String id) {
		this.id = id;
		return this;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public BaseModel setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
		return this;
	}

	public String getDateModified() {
		return dateModified;
	}

	public BaseModel setDateModified(String dateModified) {
		this.dateModified = dateModified;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public BaseModel setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public BaseModel setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
		return this;
	}

}
