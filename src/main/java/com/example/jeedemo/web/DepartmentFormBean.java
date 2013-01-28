/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jeedemo.web;

import com.example.jeedemo.domain.Department;

import com.example.jeedemo.service.DepartmentManager;
import java.io.Serializable;
import javax.faces.bean.SessionScoped;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Piotr
 */
@SessionScoped
@Named("departmentBean")
public class DepartmentFormBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Department department = new Department();
    private ListDataModel<Department> departments = new ListDataModel<Department>();
    
    @Inject
    DepartmentManager dm;
    
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
    
    public String addDepartment(){
        dm.addDepartment(department);
        return "allDepartments?faces-redirect=true";
    }
    
}
