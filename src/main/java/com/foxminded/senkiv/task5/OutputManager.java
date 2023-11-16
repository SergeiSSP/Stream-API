package com.foxminded.senkiv.task5;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class OutputManager {
    private AbbreviationsHandler handler;
    private static final int POSITION_LENGTH = 2;
    private static final int NAME_LENGTH = 20;
    private static final int BRAND_LENGTH = 30;
    private final AtomicInteger counter = new AtomicInteger();
    private final StringBuilder sb = new StringBuilder("\n");

    @Autowired
    public void setAbbreviationHandler(AbbreviationsHandler handler){
        this.handler = handler;
    }
    public String entryCreation(Map.Entry<String, Double> score, int pos){

        String[] nameOfRiderAndBrand = handler.getEntry(score.getKey()).split(",");
        Double time = score.getValue();
        return String.format("%s%s%s %f%n", position(pos), name(nameOfRiderAndBrand[0]), brand(nameOfRiderAndBrand[1]), time);
    }

    public String reportStatistics(Map<String, Double> tablesScore) {
        handler.parseAbbreviations();
        Stream<Map.Entry<String, Double>> result = tablesScore.entrySet().stream();
        result.sorted(Map.Entry.comparingByValue())
                .forEach(score -> {
                    int pos = counter.incrementAndGet();
                    sb.append(entryCreation(score, pos));
                    if (pos == 15) {
                        sb.append(looserLine()).append("\n");
                    }
                });
        return sb.toString();
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
        int TOTAL_LENGTH = POSITION_LENGTH + NAME_LENGTH + BRAND_LENGTH;
        return "-".repeat(TOTAL_LENGTH);
    }
}
