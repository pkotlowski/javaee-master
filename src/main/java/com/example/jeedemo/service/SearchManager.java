package com.example.jeedemo.service;


import java.util.List;

import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Car;



@Stateless
public class SearchManager {

	@PersistenceContext
	EntityManager em1;

			
	/*@SuppressWarnings("unchecked")
	public List<Car> getAvailableCars() {
		return em1.createNamedQuery("car.findByName").getResultList();
	}*/
	
	@SuppressWarnings("unchecked")
	public List<Car> getSearchResults(String make){
		return em1.createNamedQuery("car.findByName").setParameter(1, make).getResultList();
	}


	


}
