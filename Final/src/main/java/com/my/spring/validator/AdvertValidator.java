package com.my.spring.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.my.spring.pojo.Product;

@Component
public class AdvertValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(Product.class);
	}

	public void validate(Object obj, Errors errors) {
		Product newAdvert = (Product) obj;

		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title",
		// "error.invalid.category", "Category Required");
	}
}
