package com.foxminded.senkiv;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class AbreviationsHandler {
    private Stream<String> stream;
    private HashMap<String, String> map = new HashMap<>();

    public AbreviationsHandler(Stream<String> stream){
        this.stream = stream;
        parseAbbreviations();
    }

    public void parseAbbreviations(){
        stream.forEach((line)->
        {
            String[] arr = line.split("_");
            map.put(arr[0], String.format("%s, %s", arr[1], arr[2]));
        });
        this.map = map;
    }

    public String getEntry(String name){
        return map.get(name);
    }
}
