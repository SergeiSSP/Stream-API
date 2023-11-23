package com.foxminded.senkiv.task5;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.foxminded.senkiv.task5.App.ABBREVIATIONS;

public class OutputManager {
    private static final int POSITION_LENGTH = 2;
    private static final int NAME_LENGTH = 20;
    private static final int BRAND_LENGTH = 30;
    private final AtomicInteger counter = new AtomicInteger();


    public String entryCreation(Map.Entry<String, Double> score, int pos) {
		Map<String, String> abbreviationsMap = AbbreviationsHandler.parseAbbreviations(ABBREVIATIONS);
        String[] nameOfRiderAndBrand = AbbreviationsHandler.getEntry(score.getKey(), abbreviationsMap).split(",");
        Double time = score.getValue();
        String result = String.format("%n%s%s%s %f", position(pos), name(nameOfRiderAndBrand[0]), brand(nameOfRiderAndBrand[1]), time);
		if(pos == 15){
			result += looserLine();
		}
		return result;
    }

    public String reportStatistics(Map<String, Double> tablesScore)  {
        Stream<Map.Entry<String, Double>> result = tablesScore.entrySet().stream();
		return result
			.sorted(Map.Entry.comparingByValue())
			.map(score -> entryCreation(score, counter.incrementAndGet()))
			.collect(Collectors.joining());
    }

    private String position(int position){
        int numberOfSpaces = POSITION_LENGTH - String.valueOf(position).length();
        return String.format("|%s%s. ", " ".repeat(numberOfSpaces), position);
    }

    private String name(String name){
        int numberOfSpaces = NAME_LENGTH - name.length();
        return String.format("%s%s|", name, " ".repeat(numberOfSpaces));
    }

    private String brand(String brand){
        int numberOfSpaces = BRAND_LENGTH - brand.length();
        return String.format("%s%s", brand, " ".repeat(numberOfSpaces));

    }

    private String looserLine(){
        int totalLength = POSITION_LENGTH + NAME_LENGTH + BRAND_LENGTH;
        return "\n" + "-".repeat(totalLength);
    }
}
