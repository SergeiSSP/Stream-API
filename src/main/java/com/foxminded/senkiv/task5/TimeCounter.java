package com.foxminded.senkiv.task5;


import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.foxminded.senkiv.task5.App.END_FILE;
import static com.foxminded.senkiv.task5.App.START_FILE;

public class TimeCounter {
	private FileReader fileReader;
	@Autowired
	public void setFileReader(FileReader fileReader){
		this.fileReader = fileReader;
	}

	public Map<String, Double> findResultsOfRace() {
		Iterator<String> startIterator = fileReader.createIterator(START_FILE);
		Iterator<String> endIterator = fileReader.createIterator(END_FILE);
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