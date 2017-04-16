package com.my.spring.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.my.spring.dao.UserDAO;
import com.my.spring.exception.UserException;
import com.my.spring.pojo.User;

public class UserValidator implements Validator {

	public boolean supports(Class aClass) {
		return aClass.equals(User.class);
	}

	public void validate(Object obj, Errors errors) {
		User user = (User) obj;
		System.out.println("Inside the validator");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "error.invalid.user", "Full Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "error.invalid.user", "User Name Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email.emailAddress", "error.invalid.email.emailAddress",
				"Email Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.streetAddress", "error.invalid.address.streetAddress",
//				"Street Address Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.city", "error.invalid.address.city",
//				"City Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.state", "error.invalid.address.state",
//				"State Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.country", "error.invalid.address.country",
//				"Country Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.zipCode", "error.invalid.address.zipCode",
//				"Zip Code Required");
//		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "address.addressType", "error.invalid.address.addressType",
//				"Required");
		// check if user exists
		
	}
}
