package com.foxminded.senkiv.task5;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import static com.foxminded.senkiv.task5.StringValidator.validateInputString;


public class LinesHandler {
    private LinesHandler(){}

    public  static LocalTime getTime(String line){
		validateInputString(line);
        String[] arr;
		LocalTime result;
		try {
			arr = line.split("_");
			result = LocalTime.parse(arr[1]);
			return result;
		}catch(DateTimeParseException e){
			throw new IllegalArgumentException("This file cannot be parsed. Please check file formatting");
		}
	}

    public static String getName(String line){
		validateInputString(line);
        String[] arr = line.split("\\d");
		String result = arr[0];
		validateAbbreviationName(result);
        return result;
    }

	public static void validateAbbreviationName(String name){
		if(name.length() != 3 && !name.equals(name.toUpperCase())){
			throw new IllegalArgumentException("This file cannot be parsed. Please check file formatting");
		}
	}
}
