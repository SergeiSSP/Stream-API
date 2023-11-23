package com.foxminded.senkiv.task5;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.foxminded.senkiv.task5.StringValidator.validateInputString;

public class LinesHandler {
	private static final int END_INDEX_OF_NAME_SUBSTRING = 3;
    private LinesHandler(){}

    public  static LocalTime getTime(String line){
		validateInputString(line);
        String[] arr;
		LocalTime result;
		try {
			arr = line.split(	"_");
			result = LocalTime.parse(arr[1]);
			return result;
		}catch(DateTimeParseException e){
			throw new IllegalArgumentException("This file cannot be parsed. Please check file formatting");
		}
	}

	public static String getBrandName(String line){
		validateInputString(line);
		String result = line.substring(0, END_INDEX_OF_NAME_SUBSTRING);
		validateAbbreviationName(result);
		return result;
	}

	public static Map<String, LocalTime> getFormattedResult(Stream<String> stream){
		return stream
			.map(line -> Map.entry(getBrandName(line), getTime(line)))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}


	public static void validateAbbreviationName(String name){
		if(name.length() != 3 && !name.equals(name.toUpperCase())){
			throw new IllegalArgumentException("This file cannot be parsed. Please check file formatting");
		}
	}
}
