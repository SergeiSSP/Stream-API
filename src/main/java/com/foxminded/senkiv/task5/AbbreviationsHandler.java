package com.foxminded.senkiv.task5;

import java.util.Map;
import java.util.stream.Collectors;

public class AbbreviationsHandler {
	private AbbreviationsHandler(){}
    public static Map<String, String> parseAbbreviations(String abbreviationsUri) {
		return FileReader.createStream(abbreviationsUri)
			.map( line -> {
			String[] arr = line.split("_");
			return Map.entry(arr[0], String.format("%s, %s", arr[1], arr[2]));
		}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public static String getEntry(String name, Map<String, String> map){
        return map.get(name);
    }
}
