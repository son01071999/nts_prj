package com.code.nts_prj.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	private static final Pattern PHONE_NUMBER_PATTERN =
			Pattern.compile("^\\+(?:[0-9]●?){6,14}[0-9]$");

	@Override
	public void initialize(PhoneNumber constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String phoneNumber, ConstraintValidatorContext context) {
		return phoneNumber != null && PHONE_NUMBER_PATTERN.matcher(phoneNumber).matches();
	}
}
