package com.foxminded.senkiv;

import com.foxminded.senkiv.config.SpringConfiguration;
import com.foxminded.senkiv.task5.AbbreviationsHandler;
import com.foxminded.senkiv.task5.FileReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SpringConfiguration.class)
class AbbreviationsHandlerTest {
	private static AbbreviationsHandler abbreviationsHandler;
	private static FileReader fileReader;
	@BeforeAll
	static void setUp(ApplicationContext applicationContext){
		abbreviationsHandler = applicationContext.getBean(AbbreviationsHandler.class);
		fileReader= applicationContext.getBean(FileReader.class);
	}

	@Test
	void abbreviationHandler_shouldParseSameAmountOfAbbreviationsThatWasInOriginalFile(){
		abbreviationsHandler.parseAbbreviations("src/test/resources/abbreviations.txt");
		int sizeExpected = fileReader.createStream("src/test/resources/abbreviations.txt").toArray().length;

		int actualSizeOfMap = abbreviationsHandler.parseAbbreviations("src/test/resources/abbreviations.txt").size();
		assertEquals(sizeExpected, actualSizeOfMap);
	}

	@ParameterizedTest
	@CsvFileSource(resources= "/AbbreviationsForReference.csv", numLinesToSkip = 1)
	void abreviationsHandler_shouldReturnCorrectNameForEachAbbreviation(String input, String expected) {
		Map<String, String> map = abbreviationsHandler.parseAbbreviations("src/test/resources/abbreviations.txt");
		assertEquals(expected, abbreviationsHandler.getEntry(input, map));
	}
}
