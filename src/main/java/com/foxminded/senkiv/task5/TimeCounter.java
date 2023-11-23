package com.foxminded.senkiv.task5;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.foxminded.senkiv.task5.FileReader.createStream;
import static com.foxminded.senkiv.task5.LinesHandler.getFormattedResult;

public class TimeCounter {

	public Map<String, Double> findResultsOfRace(String startUri, String endUri) {
		Stream<String> startStream = createStream(startUri);
		Stream<String> endStream = createStream(endUri);

		Map<String, LocalTime> startInfo = getFormattedResult(startStream);
		Map<String, LocalTime> endInfo = getFormattedResult(endStream);

		Stream<String> entries = startInfo.keySet().stream();

		return entries
			.map(key ->  Map.entry(key, Duration.between(startInfo.get(key), endInfo.get(key)).toMillis() / 1_000.0))
			.collect(Collectors.toMap( Map.Entry::getKey, Map.Entry::getValue));
    }
}