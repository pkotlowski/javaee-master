package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Driver;


/* 
 * This is a Stateless EJB Bean
 * All its methods are transactional
 */
@Stateless
public class SellingManager {

	@PersistenceContext
	EntityManager em;

	public void sellCar(Long driverId, Long carId) {

		Driver driver = em.find(Driver.class, driverId);
		Car car = em.find(Car.class, carId);
		car.setSold(true);

		driver.getCars().add(car);
	}
	
	public void deleteCar(Long carId) {

		
		Car car = em.find(Car.class, carId);
		em.remove(car);
	}

	@SuppressWarnings("unchecked")
	public List<Car> getAvailableCars() {
		return em.createNamedQuery("car.unsold").getResultList();
	}

	public void disposeCar(Driver driver, Car car) {

		driver = em.find(Driver.class, driver.getId());
		car = em.find(Car.class, car.getId());

		Car toRemove = null;
		// lazy loading here (driver.getCars)
		for (Car aCar : driver.getCars())
			if (aCar.getId().compareTo(car.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			driver.getCars().remove(toRemove);
		
		car.setSold(false);
	}
}
