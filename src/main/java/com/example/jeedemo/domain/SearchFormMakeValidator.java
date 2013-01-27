package com.example.jeedemo.domain;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
 
@FacesValidator("com.example.jeedemo.domain.SearchFormMakeValidator")
public class SearchFormMakeValidator implements Validator{

	private String SearchFormPattern ="[a-zA-Z]*";
	private Pattern pattern;
	private Matcher matcher;	
	
	@Override
	public void validate(FacesContext context, UIComponent component,
			Object value) throws ValidatorException {
			
			String searchFormContent= value.toString();
			pattern = Pattern.compile(SearchFormPattern);
			matcher = pattern.matcher(searchFormContent);
						
			if(searchFormContent.equals(""))
			{	 						
				FacesMessage msg = 	new FacesMessage("Pole nie może być puste");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg); 
			}
			if(searchFormContent.contains(" "))
			{
				FacesMessage msg = 	new FacesMessage("Pole nie może zawierać spacji");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);				
			}
			
			if(!matcher.matches())
			{	 
				FacesMessage msg = 	new FacesMessage("Pole zawiera niedozwolone znaki");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);	 
			}
			if(searchFormContent.length()<2 || searchFormContent.length()>16)
			{
				FacesMessage msg = 	new FacesMessage("Pole nie spełnia wymagań długości");
				msg.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(msg);
			}

		}
}
 

 
	
