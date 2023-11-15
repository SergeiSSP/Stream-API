package com.foxminded.senkiv;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Stream;

public class TimeCounter {
    public Map<String, Double> findTheFastest(Stream<String> start, Stream<String> end){
        Map<String, Double> tableScore = new HashMap<>();
        Iterator<String> itr1 = start.sorted()
                .iterator();
        Iterator<String> itr2 = end.sorted()
                .iterator();
        while(itr1.hasNext() && itr2.hasNext()){
            String s1 = itr1.next();
            String s2 = itr2.next();
            if (!LinesHandler.getName(s1).equals(LinesHandler.getName(s2))) {
                System.out.println("Something went wrong");
            }
            LocalTime startTime = LinesHandler.getTime(s1);
            LocalTime endTime = LinesHandler.getTime(s2);
            double score = Duration.between(startTime, endTime).toMillis() / 1_000.0;
            tableScore.put(LinesHandler.getName(s1), score);
        }
        return tableScore;
    }
}