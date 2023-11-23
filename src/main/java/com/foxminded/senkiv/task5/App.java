package com.foxminded.senkiv.task5;

import com.foxminded.senkiv.config.SpringConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class App
{
    private static final Logger logger = LogManager.getLogger(App.class);
    public static final String START_FILE = "src/main/resources/start.log";
    public static final String END_FILE = "src/main/resources/end.log";
    public static final String ABBREVIATIONS = "src/main/resources/abbreviations.txt";

    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfiguration.class);
        TimeCounter timeCounter = context.getBean(TimeCounter.class);
        OutputManager outputManager = context.getBean(OutputManager.class);

        String result = outputManager.reportStatistics(timeCounter.findResultsOfRace(START_FILE, END_FILE));
        logger.info(result);
	}
}
