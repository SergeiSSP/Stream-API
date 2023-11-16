package com.foxminded.senkiv;

import com.foxminded.senkiv.config.SpringConfiguration;
import com.foxminded.senkiv.task5.TimeCounter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SpringConfiguration.class)
class TimeCounterTest {
	private static TimeCounter counter;
	private static ApplicationContext context;
	@BeforeAll
	static void setContext(ApplicationContext contextToAssign){
		context = contextToAssign;
	}
	@BeforeEach
	void setUp(){
		counter = context.getBean(TimeCounter.class);
	}

	@Test
	void timeCounter_shouldReturnSameAmountOfRidersAsWasInFile(){
		Stream<String> stream = (Stream<String>) context.getBean("startStream");
		Map<String, Double> map = counter.findTheFastest();
		assertEquals(stream.toArray().length, map.size());
	}
}
