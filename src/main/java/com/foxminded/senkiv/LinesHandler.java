package com.foxminded.senkiv;

import java.time.LocalTime;


public class LinesHandler {

    public  static LocalTime getTime(String line){
        String[] arr = line.split("_");
        return LocalTime.parse(arr[1]);
    }

    public static String getName(String line){
        String[] arr = line.split("[0-9]");
        return arr[0];
    }
}
