package br.com.utfpr.eventos.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import br.com.utfpr.eventos.models.Event;

public class EventValidation implements Validator {

	public boolean supports(Class<?> clazz) {
		return Event.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmpty(errors, "name", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "price", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "date", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "max", "field.required");
		ValidationUtils.rejectIfEmpty(errors, "adress", "field.required");
	}

}
