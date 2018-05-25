package br.com.utfpr.eventos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.utfpr.eventos.models.User;

public class UserValidation implements Validator {

	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz); 
	}

	public void validate(Object target, Errors errors) {
		
		ValidationUtils.rejectIfEmpty(errors, "name", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "email", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "document", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "password", "field.required");
		
	}
}
