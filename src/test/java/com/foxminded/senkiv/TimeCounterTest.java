package com.foxminded.senkiv;

import com.foxminded.senkiv.config.SpringConfiguration;
import com.foxminded.senkiv.task5.FileReader;
import com.foxminded.senkiv.task5.TimeCounter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SpringConfiguration.class)
class TimeCounterTest {
	private static TimeCounter counter;

	@BeforeAll
	static void setContext(ApplicationContext contextToAssign){
		counter = contextToAssign.getBean(TimeCounter.class);
	}

	@Test
	void findTheFastest_shouldReturnSameAmountOfRidersAsWasInFile() {
		Map<String, Double> map = counter.findResultsOfRace("src/test/resources/start.log", "src/test/resources/end.log");
		assertEquals(FileReader.createStream("src/test/resources/end.log").toArray().length, map.size());
	}
}
