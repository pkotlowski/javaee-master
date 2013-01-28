/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jeedemo.web;

import com.example.jeedemo.domain.Department;
import com.example.jeedemo.domain.Driver;
import com.example.jeedemo.service.AssignManager;
import com.example.jeedemo.service.DepartmentManager;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;


@SessionScoped
@Named("departmentBean")
public class DepartmentFormBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
	private Department department = new Department();
	private ListDataModel<Department> departments = new ListDataModel<Department>();
	
	private Department departmentToShow = new Department();
	private ListDataModel<Driver> memberOfDept = new ListDataModel<Driver>();
    
    @Inject
    DepartmentManager dm;
    
    @Inject
    AssignManager am;
    
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ListDataModel<Department> getAllDepartments(){        
        departments.setWrappedData(dm.getAllDepartments());
        return departments;
    }
    
    public ListDataModel<Driver> getMembers() {
	memberOfDept.setWrappedData(dm.getMembers(departmentToShow));
	return memberOfDept;
    }
    
    public String addDepartment(){
        dm.addDepartment(department);
        return "allDepartments?faces-redirect=true";
    }    
 
    public String showDetails() {
		departmentToShow = departments.getRowData();
		return "DeptDetails";
    }
    public String disposeDriver(){
	Driver driverToDispose = memberOfDept.getRowData();
	am.disposeDriver(departmentToShow, driverToDispose);
	return null;
    }
	
    
}
