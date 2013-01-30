package com.example.jeedemo.web;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Driver;
import com.example.jeedemo.service.DriverManager;
import com.example.jeedemo.service.SellingManager;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

@SessionScoped
@Named("driverBean")
public class DriverFormBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Driver driver = new Driver();
        private Driver updatedDriver  = new Driver();

    public Driver getUpdatedDriver() {
        return updatedDriver;
    }

    public void setUpdatedDriver(Driver updatedDriver) {
        this.updatedDriver = updatedDriver;
    }
	private ListDataModel<Driver> drivers = new ListDataModel<Driver>();
	
	private Driver driverToShow = new Driver();
	private ListDataModel<Car> ownedCars = new ListDataModel<Car>();


	@Inject
	private DriverManager pm;
	
	@Inject
	private SellingManager sm;

	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public ListDataModel<Driver> getAllDrivers() {
		drivers.setWrappedData(pm.getAllDrivers());
		return drivers;
	}

	public ListDataModel<Car> getOwnedCars() {
		ownedCars.setWrappedData(pm.getOwnedCars(driverToShow));
		return ownedCars;
	}
        
        public ListDataModel<Driver> getAllUnassignedDrivers() {
		drivers.setWrappedData(pm.getAllUnassignedDrivers());
		return drivers;
	}
	
	// Actions
	public String addDriver() {
		pm.addDriver(driver);
		return "showDrivers?faces-redirect=true";
		//return null;
	}

	public String deleteDriver() {
		Driver driverToDelete = drivers.getRowData();
		pm.deleteDriver(driverToDelete);
		return null;
	}
        
        public String updateDriver(){
            updatedDriver = drivers.getRowData();
            //pm.updateDriver(updatedDriver);
            return "updateDriver";
        }
        public String upD(){
            pm.updateDriver(updatedDriver);
            return "showDrivers?faces-redirect=true";
        }
        
	public String showDetails() {
		driverToShow = drivers.getRowData();
		return "details";
	}
	
	public String disposeCar(){
		Car carToDispose = ownedCars.getRowData();
		sm.disposeCar(driverToShow, carToDispose);
		return null;
	}
	
	public boolean checkPeselCorrectness(String pesel){
		
		boolean statement;
	
		/*Rozbijanie peselu na czesci*/
		
		int a= Integer.parseInt(pesel.substring(0, 1));
		int b= Integer.parseInt(pesel.substring(1, 2));
		int c= Integer.parseInt(pesel.substring(2, 3));
		int d= Integer.parseInt(pesel.substring(3, 4));
		int e= Integer.parseInt(pesel.substring(4, 5));
		int f= Integer.parseInt(pesel.substring(5, 6));
		int g= Integer.parseInt(pesel.substring(6, 7));
		int h= Integer.parseInt(pesel.substring(7, 8));
		int i= Integer.parseInt(pesel.substring(8, 9));
		int j= Integer.parseInt(pesel.substring(9, 10));
		int k= Integer.parseInt(pesel.substring(10, 11));
		
		long sum = 1 *a + 3*b + 7*c + 9*d + 1*e + 3*f + 7*g + 9*h + 1*i + 3*j;
		
		long wynik = 10-(sum%10);
		if(wynik==k)
		{
			statement=true;
		}
		else
		{
			statement=false;	
		}
		
		
		return statement;
	}
	
	public void uniquePin(FacesContext context, UIComponent component,
			Object value) {

		UIInput dateOfBirth = (UIInput) component.getAttributes().get("dateOfBirth");
		
		String pesel = (String) value;
		//String dateNowString = DateFormat.getDateInstance().format(dateOfBirth);
		//String year = (String) dateOfBirth.getValue();
		if(pesel.length() ==11){
		if(checkPeselCorrectness(pesel))
		{
			for (Driver driver : pm.getAllDrivers())
			{
				if (driver.getPesel().equalsIgnoreCase(pesel))
				{
					FacesMessage message = new FacesMessage(
							"Driver with this PIN already exists in database");
					message.setSeverity(FacesMessage.SEVERITY_ERROR);
					throw new ValidatorException(message);
				}
			}
		}
		else
		{
			FacesMessage message = new FacesMessage(
					"PESEL SIĘ NIE ZGADZA!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
		}
		else
		{
			FacesMessage message = new FacesMessage(
					"za krotki!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
}
