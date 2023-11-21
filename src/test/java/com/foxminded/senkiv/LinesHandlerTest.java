package com.foxminded.senkiv;

import com.foxminded.senkiv.exceptions.RaceApplicationRuntimeException;
import com.foxminded.senkiv.task5.LinesHandler;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LinesHandlerTest {
	@Test
	void linesHandler_shouldParseTimeFromStringCorrectly(){
		String input = "MES2018-05-24_12:05:58.778";
		LocalTime expectedResult = LocalTime.parse("12:05:58.778");
		assertEquals(expectedResult, LinesHandler.getTime(input));
	}

	@Test
	void linesHandler_shouldThrowIllegalArgumentExceptionIfItCannotParseTheLine(){
		assertThrows(IllegalArgumentException.class, () -> LinesHandler.getTime("MES2018-05-24_Hello"));
	}

	@Test
	void linesHandler_shouldParseNameOfRiderCorrectly(){
		String input = "MES2018-05-24_12:05:58.778";
		String actualResult = LinesHandler.getName(input);
		assertEquals("MES", actualResult);
	}

	@Test
	void linesHandler_shouldThrowIllegalArgumentExceptionIfItCannotParseName(){
		assertThrows(IllegalArgumentException.class, () -> LinesHandler.validateAbbreviationName("saW2"));
	}

	@Test
	void getName_shouldThrowIllegalArgumentExceptionIfParameterIsNull(){
		assertThrows(RaceApplicationRuntimeException.class, ()-> LinesHandler.getName(null));
	}

	@Test
	void getTime_shouldThrowIllegalArgumentExceptionIfParameterIsNull(){
		assertThrows(RaceApplicationRuntimeException.class, ()-> LinesHandler.getTime(null));
	}

	@Test
	void getName_shouldThrowIllegalArgumentExceptionIfParameterIsEmptyString(){
		assertThrows(RaceApplicationRuntimeException.class, ()-> LinesHandler.getName(null));
	}

	@Test
	void getTime_shouldThrowIllegalArgumentExceptionIfParameterIsString(){
		assertThrows(RaceApplicationRuntimeException.class, ()-> LinesHandler.getTime(null));
	}

}
