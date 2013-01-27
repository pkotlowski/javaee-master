package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Driver;
import com.example.jeedemo.service.DriverManager;
import com.example.jeedemo.service.SellingManager;


@SessionScoped
@Named("saleBean")
public class SaleFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private SellingManager sm;

	@Inject
	private DriverManager dm;

	private Long carId;
	private Long driverId;
	
	public Long getCarId() {
		return carId;
	}
	public void setCarId(Long carId) {
		this.carId = carId;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public List<Car> getAvailableCars() {
		return sm.getAvailableCars();
	}

	public List<Driver> getAllDrivers() {
		return dm.getAllDrivers();
	}

	public String sellCar() {
		sm.sellCar(driverId, carId);
		return null;
	}



}
