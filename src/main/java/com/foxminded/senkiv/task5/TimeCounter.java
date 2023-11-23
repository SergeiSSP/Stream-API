package com.foxminded.senkiv.task5;

import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.foxminded.senkiv.task5.LinesHandler.getFormattedResult;

public class TimeCounter {
	private FileReader fileReader;

	@Autowired
	public void setFileReader(FileReader fileReader){
		this.fileReader = fileReader;
	}


	public Map<String, Double> findResultsOfRace(String startUri, String endUri) {
		Stream<String> startStream = fileReader.createStream(startUri);
		Stream<String> endStream = fileReader.createStream(endUri);

		Map<String, LocalTime> startInfo = getFormattedResult(startStream);
		Map<String, LocalTime> endInfo = getFormattedResult(endStream);

		Stream<String> entries = startInfo.keySet().stream();

		return entries
			.map(key ->  Map.entry(key, Duration.between(startInfo.get(key), endInfo.get(key)).toMillis() / 1_000.0))
			.collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue));
    }
}