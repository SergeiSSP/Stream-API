package com.foxminded.senkiv.task5;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TimeCounter {
	private Iterator<String> startIterator;
	private Iterator<String> endIterator;

    @Autowired
    @Qualifier("startIterator")
    private void setStart(Iterator<String> start) {
        this.startIterator = start;
    }

    @Autowired
    @Qualifier("endIterator")
    private void setEnd(Iterator<String> end) {
        this.endIterator = end;
    }

	public Map<String, Double> findTheFastest(){
		Map<String, Double> tableScore = new HashMap<>();
        while(startIterator.hasNext() && endIterator.hasNext()){
            String startOfRace = startIterator.next();
            String endOfRace = endIterator.next();
            double score = Duration.between(LinesHandler.getTime(startOfRace), LinesHandler.getTime(endOfRace)).toMillis() / 1_000.0;
            tableScore.put(LinesHandler.getName(startOfRace), score);
        }
        return tableScore;
    }
}