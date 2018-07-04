package br.com.utfpr.eventos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.utfpr.eventos.models.Search;

public class SearchValidation implements Validator {

	public boolean supports(Class<?> clazz) {
		return Search.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "search", "field.required");
		
	}

}
