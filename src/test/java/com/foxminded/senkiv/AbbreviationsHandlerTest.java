package com.foxminded.senkiv;

import com.foxminded.senkiv.config.SpringConfiguration;
import com.foxminded.senkiv.task5.AbbreviationsHandler;
import com.foxminded.senkiv.task5.FileReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes=SpringConfiguration.class)
class AbbreviationsHandlerTest {
	static AbbreviationsHandler handler;

	@BeforeEach
	void setUp(ApplicationContext context){
		handler = context.getBean(AbbreviationsHandler.class);

	}

	@Test
	void abbreviationHandler_shouldParseSameAmountOfAbbreviationsThatWasInOriginalFile(ApplicationContext context) throws IOException {
		FileReader fileReader = context.getBean(FileReader.class);
		handler.parseAbbreviations();
		int sizeExpected = fileReader.createStream("src/test/resources/abbreviations.txt").toArray().length;

		int actualSizeOfMap = handler.parseAbbreviations().size();
		assertEquals(sizeExpected, actualSizeOfMap);
	}

	@ParameterizedTest
	@CsvFileSource(resources= "/AbbreviationsForReference.csv", numLinesToSkip = 1)
	void abreviationsHandler_shouldReturnCorrectNameForEachAbbreviation(String input, String expected) throws IOException {
		Map<String, String> map = handler.parseAbbreviations();
		assertEquals(expected, handler.getEntry(input, map));
	}






}
