package com.example.jeedemo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "driver.all", query = "Select p from Driver p"),
        @NamedQuery(name = "driver.unassigned", query="SELECT D FROM Driver D WHERE D.isDriverAssigned=FALSE")
})
public class Driver implements Serializable {

	private Long id;

	private String firstName = "";
	private String lastName="";
	private String pesel = "";
	private String eMail="";
	private Date dateOfBirth=new Date();
	private Boolean isDriverAssigned=false;

 

	private List<Car> cars = new ArrayList<Car>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Size(min = 2, max = 20)
	@Pattern(regexp="[a-zA-Z]*", message="niepoprawny format imienia")
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Size(min=2, max=20)
	@Pattern(regexp="[a-zA-Z]*", message="niepoprawny format nazwiska")
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Pattern(regexp="[0-9]{11}", message="niepoprawny format numeru pesel")
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	
	@Temporal(TemporalType.DATE)
	@Past
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
        
        public Boolean getIsDriverAssigned() {
            return isDriverAssigned;
        }

        public void setIsDriverAssigned(Boolean isDriverAssigned) {
            this.isDriverAssigned = isDriverAssigned;
        }

	

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Car> getCars() {
		return cars;
	}
	public void setCars(List<Car> cars) {
		this.cars = cars;
	}


}
