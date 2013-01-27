package com.example.jeedemo.service;


import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Driver;


@Stateless
public class CarManager {

	@PersistenceContext
	EntityManager em1;

	public void addCar(Car car) {
		car.setId(null);
		em1.persist(car);
	}

	public void deleteCar(Car car) {
		try
		{
			car = em1.find(Car.class, car.getId());
			em1.remove(car);
			
			
		}
		catch(Exception ex)
		{
			FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("hs"+ex);
			facesContext.addMessage(null, facesMessage);
		}
		//em.remove(getAllCars());
	}

	@SuppressWarnings("unchecked")
	public List<Car> getAvailableCars() {
		return em1.createNamedQuery("car.unsold").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Car> getPremiumGroupCars() {
		return em1.createNamedQuery("car.premiumGroup").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Car> getStandardGroupCars() {
		return em1.createNamedQuery("car.standardGroup").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Car> getEconomicGroupCars() {
		return em1.createNamedQuery("car.econoGroup").getResultList();
	}

}
