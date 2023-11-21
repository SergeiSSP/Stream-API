package com.foxminded.senkiv.task5;

import com.foxminded.senkiv.exceptions.RaceApplicationRuntimeException;

public class StringValidator {
	private StringValidator(){}
	public static void validateInputString(String stringToValidate){
		if (stringToValidate == null){
			throw new RaceApplicationRuntimeException("Cannot process null value.");
		}
		if(stringToValidate.trim().isEmpty()){
			throw new RaceApplicationRuntimeException("Path to file is empty.");
		}
	}
}
