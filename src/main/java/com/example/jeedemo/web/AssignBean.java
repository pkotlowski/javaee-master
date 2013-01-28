package com.example.jeedemo.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Car;
import com.example.jeedemo.domain.Department;
import com.example.jeedemo.domain.Driver;
import com.example.jeedemo.service.AssignManager;
import com.example.jeedemo.service.DepartmentManager;
import com.example.jeedemo.service.DriverManager;
import com.example.jeedemo.service.SellingManager;


@SessionScoped
@Named("assignBean")
public class AssignBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AssignManager am;

	@Inject
	private DriverManager dm;
        
        @Inject
	private DepartmentManager dptm;

	private Long departmentId;
        private Long driverId;
        
        public Long getDepartmentId() {
        return departmentId;
        }
        public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
        }
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}

	public List<Department> getAllDepartments() {
		return dptm.getAllDepartments();
	}

	public List<Driver> getAllUnassignedDrivers() {
		return dm.getAllUnassignedDrivers();
	}
 
        
	public String assignDriver() {
		//sm.sellCar(driverId, carId);
                am.assignDriver(driverId, departmentId);
		return null;
	}



}
