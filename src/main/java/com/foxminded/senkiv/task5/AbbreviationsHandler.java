package com.foxminded.senkiv.task5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class AbbreviationsHandler {
    private Stream<String> stream;
    public final Map<String, String> map = new HashMap<>();

    @Autowired
    @Qualifier("abbreviationsStream")
    public void setStreamFromAbbreviationFile(Stream<String> stream){
        this.stream = stream;
    }

	public Stream<String> getStream(){
		return this.stream;
	}


    public void parseAbbreviations(){
        stream.forEach(line->
        {
            String[] arr = line.split("_");
            map.put(arr[0], String.format("%s, %s", arr[1], arr[2]));
        });
    }

    public String getEntry(String name){
        return map.get(name);
    }
}
