package com.example.jeedemo.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Driver;

@Stateless
public class DriverManager {

	@PersistenceContext
	EntityManager em;

	public void addDriver(Driver driver) {
		driver.setId(null);
		em.persist(driver);
	}

	public void deleteDriver(Driver driver) {
		driver = em.find(Driver.class, driver.getId());
		em.remove(driver);
	}

	@SuppressWarnings("unchecked")	//wymuszenie poprawnosci na kompilatorze
	public List<Driver> getAllDrivers() {
		return em.createNamedQuery("driver.all").getResultList();
	}

	public List<Car> getOwnedCars(Driver driver) {
		driver = em.find(Driver.class, driver.getId());
		// lazy loading here - try this code without this (shallow) copying
		List<Car> cars = new ArrayList<Car>(driver.getCars());
		return cars;
	}

}
