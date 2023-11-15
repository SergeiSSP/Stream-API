package com.foxminded.senkiv;

import java.io.IOException;
import java.util.Map;
import java.util.stream.Stream;

public class App 
{
    public static void main( String[] args ){

        Stream<String> start = new FileReader("src/main/resources/start.log").createStream();
        Stream<String> end = new FileReader("src/main/resources/end.log").createStream();
        AbreviationsHandler ah = new AbreviationsHandler(new FileReader("src/main/resources/abbreviations.txt").createStream());
        TimeCounter tc = new TimeCounter();
        OutputManager om = new OutputManager(ah);

        Map<String, Double> tableScore = tc.findTheFastest(start, end);
        String result = om.reportStatistics(tableScore).toString();
        System.out.println(result);
    }
}
