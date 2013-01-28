/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.jeedemo.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Piotr
 */
@NamedQuery(name="department.all", query="SELECT d from Department d")
@Entity
public class Department {
    private Long id;
    private String name;

    private List<Driver> drivers =new ArrayList<Driver>();


    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }
}
