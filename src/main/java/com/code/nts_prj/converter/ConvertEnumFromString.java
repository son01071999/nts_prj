package com.code.nts_prj.converter;

public class ConvertEnumFromString {
	private static final String INVALID_ENUM_STRING = "Invalid enum value";

	public static <E extends Enum<E>> E validateEnumFromString(
			Class<E> enumClass, String inputString) {
		try {
			return Enum.valueOf(enumClass, inputString.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(INVALID_ENUM_STRING);
		}
	}
}
