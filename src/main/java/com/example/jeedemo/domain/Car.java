package com.example.jeedemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


//Konewncja nazywania: encja.zapytanie
@Entity
@NamedQueries({
@NamedQuery(name = "car.unsold", query = "Select c from Car c where c.sold=false"),
@NamedQuery(name="car.findByName", query=" SELECT C FROM Car c where c.make=?"),
@NamedQuery(name="car.premiumGroup", query="SELECT C FROM Car C WHERE C.price>=50000"),
@NamedQuery(name="car.standardGroup", query="SELECT C FROM Car C WHERE C.price BETWEEN 25000 AND 50000"),
@NamedQuery(name="car.econoGroup", query="SELECT C FROM Car C WHERE C.price<=25000")
})
public class Car {
	
	private Long id;
	private String make;
	private String model;
	private int yearOfProduction;
	private double engineCap;
	private double price;
	private String fuelType;
	private Boolean sold = false;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public Boolean getSold() {
		return sold;
	}
	public void setSold(Boolean sold) {
		this.sold = sold;
	}
	public int getYearOfProduction() {
		return yearOfProduction;
	}
	public void setYearOfProduction(int yearOfProduction) {
		this.yearOfProduction = yearOfProduction;
	}
	public double getEngineCap() {
		return engineCap;
	}
	public void setEngineCap(double engineCap) {
		this.engineCap = engineCap;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
}
