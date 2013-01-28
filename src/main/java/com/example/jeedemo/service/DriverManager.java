package com.example.jeedemo.service;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Driver;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DriverManager {

	@PersistenceContext
	EntityManager em;

	public void addDriver(Driver driver) {
		driver.setId(null);
		em.persist(driver);
	}

	public void deleteDriver(Driver driver) {
                //deleteDriverFromDepartment(driver, null);
		driver = em.find(Driver.class, driver.getId());
                
                if(driver.getIsDriverAssigned()==true){
                        FacesContext facesContext = FacesContext.getCurrentInstance();
			FacesMessage facesMessage = new FacesMessage("POdany kierowca jest przypisany do oddziału- Należy go najpierw usunąć");
			facesContext.addMessage(null, facesMessage);
                }
                else{             
		em.remove(driver);
                }
	}
        @SuppressWarnings("unchecked")	//wymuszenie poprawnosci na kompilatorze
	public List<Driver> getAllDrivers() {
		return em.createNamedQuery("driver.all").getResultList();
	}
        @SuppressWarnings("unchecked")	//wymuszenie poprawnosci na kompilatorze
	public List<Driver> getAllUnassignedDrivers() {
		return em.createNamedQuery("driver.unassigned").getResultList();
	}

	public List<Car> getOwnedCars(Driver driver) {
		driver = em.find(Driver.class, driver.getId());
		// lazy loading here - try this code without this (shallow) copying
		List<Car> cars = new ArrayList<Car>(driver.getCars());
		return cars;
	}

}
