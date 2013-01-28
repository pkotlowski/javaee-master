package com.example.jeedemo.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Department;
import com.example.jeedemo.domain.Driver;


/* 
 * This is a Stateless EJB Bean
 * All its methods are transactional
 */
@Stateless
public class AssignManager {

	@PersistenceContext
	EntityManager em;

	public void assignDriver(Long driverId, Long departmentId) {
		Driver driver = em.find(Driver.class, driverId);		
                Department department = em.find(Department.class, departmentId);		
                driver.setIsDriverAssigned(true);		
                department.getDrivers().add(driver);
	}


	public void disposeDriver(Department department, Driver driver) {

		//driver = em.find(Driver.class, driver.getId());
		driver = em.find(Driver.class, driver.getId());
                department = em.find(Department.class, department.getId());

		Driver toRemove = null;
		// lazy loading here (driver.getCars)
		for (Driver aCar : department.getDrivers())
			if (aCar.getId().compareTo(driver.getId()) == 0) {
				toRemove = aCar;
				break;
			}

		if (toRemove != null)
			//driver.getCars().remove(toRemove);
                        department.getDrivers().remove(toRemove);
		driver.setIsDriverAssigned(false);
	}
}
