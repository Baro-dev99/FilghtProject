package com.example.dao;

import java.util.List;

import com.example.exceptions.DAOException;

public interface IDAO<T> {

	public T getById(String id) throws DAOException;

	public List<T> getAll() throws DAOException;
	
	public void add(T t) throws DAOException;

	public void remove(String id) throws DAOException;

	public void update(String id, T t) throws DAOException;
}
