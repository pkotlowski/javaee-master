package com.example.jeedemo.web;

import java.io.Serializable;


import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

import com.example.jeedemo.domain.Car;

import com.example.jeedemo.service.CarManager;
import com.example.jeedemo.service.SearchManager;


@SessionScoped
@Named("searchBean")
public class SearchBean implements Serializable {
    private static final long serialVersionUID = 1L;        
    private String searchFormMake;

    public String getSearchFormMake() {
        return searchFormMake;
    }

    public void setSearchFormMake(String searchFormMake) {
        this.searchFormMake = searchFormMake;
    }

    // a list for storing the available cars and the search results
    private ListDataModel<Car> cars = new ListDataModel<Car>();
    
    //search manager -->
    @Inject
    private SearchManager sm;

public void SearchCars(){
    cars.setWrappedData(sm.getSearchResults(searchFormMake));
    
}
   public ListDataModel<Car> getSearchResults(){
       return cars;
   }

		

}
