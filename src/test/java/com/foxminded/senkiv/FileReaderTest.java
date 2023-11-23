package com.foxminded.senkiv;

import com.foxminded.senkiv.exceptions.RaceApplicationRuntimeException;
import com.foxminded.senkiv.task5.App;
import com.foxminded.senkiv.task5.FileReader;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

	@Test
	void fileFileReader_shouldThrowIllegalArgumentExceptionIfFileWithSuchUriDoesNotExist() {
		String actualAddress = "/asd/asd/asd";
		assertThrows(RaceApplicationRuntimeException.class, ()-> FileReader.createStream(actualAddress));
	}

	@Test
	void fileFileReader_shouldThrowIllegalArgumentExceptionIfUriIsEmpty() {
		String actualAddress = "   ";

		assertThrows(RaceApplicationRuntimeException.class, () -> FileReader.createStream(actualAddress));
	}

	@Test
	void fileFileReader_shouldThrowIllegalArgumentExceptionIfUriIsNull() {
		assertThrows(RaceApplicationRuntimeException.class, () -> FileReader.createStream(null));
	}

	@Test
	void fileFileReader_shouldThrowIllegalArgumentExceptionIfFailedToReadTheFile() {

		assertThrows(RaceApplicationRuntimeException.class, () -> FileReader.createStream("NoUri/NoUri/a"));
	}

	@Test
	void fileFileReader_shouldReturnStreamIfUriWasValid() {
		String actualAddress = App.START_FILE;
		Stream<String> stream = FileReader.createStream(actualAddress);
		assertNotNull(stream);
	}
}


