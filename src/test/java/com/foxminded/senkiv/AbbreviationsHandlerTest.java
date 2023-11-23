package com.foxminded.senkiv;

import com.foxminded.senkiv.task5.AbbreviationsHandler;
import com.foxminded.senkiv.task5.FileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AbbreviationsHandlerTest {

	@Test
	void abbreviationHandler_shouldParseSameAmountOfAbbreviationsThatWasInOriginalFile(){
		AbbreviationsHandler.parseAbbreviations("src/test/resources/abbreviations.txt");
		int sizeExpected = FileReader.createStream("src/test/resources/abbreviations.txt").toArray().length;

		int actualSizeOfMap = AbbreviationsHandler.parseAbbreviations("src/test/resources/abbreviations.txt").size();
		assertEquals(sizeExpected, actualSizeOfMap);
	}

	@ParameterizedTest
	@CsvFileSource(resources= "/AbbreviationsForReference.csv", numLinesToSkip = 1)
	void abreviationsHandler_shouldReturnCorrectNameForEachAbbreviation(String input, String expected) {
		Map<String, String> map = AbbreviationsHandler.parseAbbreviations("src/test/resources/abbreviations.txt");
		assertEquals(expected, AbbreviationsHandler.getEntry(input, map));
	}
}
