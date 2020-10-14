package validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import models.Products;

public class ProductValidation implements Validator{
	
	public boolean supports(Class<?> clazz) {
		return Products.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "titulo", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "descricao", "field.required");
		if(((Products)target).getPaginas() <= 0)
			errors.rejectValue("paginas", "field.required");
		
	}

}
