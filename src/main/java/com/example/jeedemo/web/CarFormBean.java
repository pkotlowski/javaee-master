package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Driver;
import com.example.jeedemo.service.CarManager;
import com.example.jeedemo.service.DriverManager;
import com.example.jeedemo.service.SellingManager;

@SessionScoped
@Named("carBean")
public class CarFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Car car = new Car();
	private ListDataModel<Car> cars = new ListDataModel<Car>();

	@Inject
	private CarManager cm;
	
	

	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	

	public ListDataModel<Car> getAvailableCars() {
		cars.setWrappedData(cm.getAvailableCars());
		return cars;
	}
	
	public ListDataModel<Car> getPremiumGroupCars() {
		cars.setWrappedData(cm.getPremiumGroupCars());
		return cars;
	}
	public ListDataModel<Car> getStandardGroupCars() {
		cars.setWrappedData(cm.getStandardGroupCars());
		return cars;
	}
	public ListDataModel<Car> getEconomicGroupCars() {
		cars.setWrappedData(cm.getEconomicGroupCars());
		return cars;
	}
		
	// Actions
	public String addCar() {
		cm.addCar(car);
		return "allAvailableCars?faces-redirect=true";
		//return null;
	}

	public String deleteCar() {
		Car carToDelete = cars.getRowData();
		cm.deleteCar(carToDelete);
		return null;
	}
	
	
	
	
	
}
