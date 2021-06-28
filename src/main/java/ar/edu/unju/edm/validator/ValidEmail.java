package ar.edu.unju.edm.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidEmail implements ConstraintValidator<validacionEmail, String>{

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		   if (!value.endsWith("@gmail.com")) {
			if (value.endsWith("@facebook.com")) {
				return true;
			}
	       }else {
			return true;
		}
		   
		return false;
	}
	


}
