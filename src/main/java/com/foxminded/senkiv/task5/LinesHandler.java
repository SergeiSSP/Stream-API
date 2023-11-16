package com.foxminded.senkiv.task5;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class LinesHandler {
    private LinesHandler(){}

    public  static LocalTime getTime(String line){
        String[] arr = line.split("_");
		LocalTime result;
		try {
			result = LocalTime.parse(arr[1]);
		}catch(DateTimeParseException e){
			throw new IllegalArgumentException("This file cannot be parsed. Please check file formatting");
		}
        return result;
    }

    public static String getName(String line){
        String[] arr = line.split("\\d");
		String result = arr[0];
		validateName(result);
        return result;
    }

	public static void validateName(String name){
		if(name.length() != 3 && !name.equals(name.toUpperCase())){
			throw new IllegalArgumentException("This file cannot be parsed. Please check file formatting");
		}
	}
}
