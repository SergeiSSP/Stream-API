package com.foxminded.senkiv.task5;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.stream.Collectors;

public class AbbreviationsHandler {
	private FileReader fileReader;
	@Autowired
	public void setFileReader(FileReader fileReader){
		this.fileReader = fileReader;
	}
    public Map<String, String> parseAbbreviations(String abbreviationsUri) {
		return fileReader.createStream(abbreviationsUri)
			.map( line -> {
			String[] arr = line.split("_");
			return Map.entry(arr[0], String.format("%s, %s", arr[1], arr[2]));
		}
		).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public String getEntry(String name, Map<String, String> map){
        return map.get(name);
    }
}
