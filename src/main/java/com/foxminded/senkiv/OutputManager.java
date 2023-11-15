package com.foxminded.senkiv;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

public class OutputManager {
    private AbreviationsHandler ah;
    public OutputManager(AbreviationsHandler ah){
        this.ah = ah;
    }
    public String entryCreation(Map.Entry<String, Double> score, int pos){

        String[] nameOfRiderAndBrand = ah.getEntry(score.getKey()).split(",");
        Double time = score.getValue();
        String formatted = String.format("%s%s%s %f%n", position(pos), name(nameOfRiderAndBrand[0]), brand(nameOfRiderAndBrand[1]), time);
        return formatted;
    }

    public StringBuilder reportStatistics(Map<String, Double> tablesScore) {
        AtomicInteger counter = new AtomicInteger();
        StringBuilder sb = new StringBuilder();
        Stream<Map.Entry<String, Double>> result = tablesScore.entrySet().stream();
        result.sorted(Map.Entry.comparingByValue())
                .forEach(score -> {
                    int pos = counter.incrementAndGet();
                    sb.append(entryCreation(score, pos));
                    if (pos == 15) {
                        sb.append(looserLine() + "\n");
                    }
                });
        return sb;
    }

    private String position(int pos){
        int numberOfSpaces = 2 - String.valueOf(pos).length();
        return String.format("|%s%s. ", " ".repeat(numberOfSpaces), pos);
    }

    private String name(String name){
        int numberOfSpaces = 20-name.length();
        return String.format("%s%s|", name, " ".repeat(numberOfSpaces));
    }

    private String brand(String brand){
        int numberOfSpaces = 30 - brand.length();
        return String.format("%s%s", brand, " ".repeat(numberOfSpaces));

    }

    private String looserLine(){
        return "-".repeat(52);
    }
}
