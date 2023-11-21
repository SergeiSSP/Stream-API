package com.foxminded.senkiv.task5;




import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static com.foxminded.senkiv.task5.App.ABBREVIATIONS;

public class AbbreviationsHandler {
	private FileReader fileReader;
	@Autowired
	public void setFileReader(FileReader fileReader){
		this.fileReader = fileReader;
	}

    public Map<String, String> parseAbbreviations() {
		Map<String, String> map = new HashMap<>();
		Stream<String> abbreviations = fileReader.createStream(ABBREVIATIONS);
        abbreviations.forEach(line->
        {
            String[] arr = line.split("_");
            map.put(arr[0], String.format("%s, %s", arr[1], arr[2]));
        });
		return map;
    }

    public String getEntry(String name, Map<String, String> map){
        return map.get(name);
    }
}
