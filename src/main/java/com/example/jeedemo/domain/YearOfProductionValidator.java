package com.example.jeedemo.domain;


import java.util.Date;
import java.text.*;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;


 
@FacesValidator("com.example.jeedemo.domain.YearOfProductionValidator")
public class YearOfProductionValidator implements Validator{


	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
			
			Date dateNow = new Date();
			
				
			String dateNowString = DateFormat.getDateInstance().format(dateNow);
			dateNowString = dateNowString.substring(0, 4);
			int commonYear = Integer.parseInt(dateNowString);				//calculated
		 	int carProductionYear = Integer.parseInt( value.toString() ); 	//from form
		 	
		 	//1900-Ford T production year ;-)
			if((carProductionYear > commonYear) || carProductionYear<1900){
					
			FacesMessage msg = 
				new FacesMessage("błędny rok produkcji");
			msg.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(msg);
 
			}
		}
 
	}
