package com.code.nts_prj.validation;

import java.lang.reflect.Field;

public class RequestObjectValidation {
	public static boolean checkAllFieldIsNotNull(Object obj) throws IllegalAccessException {
		for (Field field : obj.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			Object value = field.get(obj);
			if (value == null) {
				return false; // if any field is null, return false
			}
		}
		return true; // if all fields are not null, return true
	}
}
