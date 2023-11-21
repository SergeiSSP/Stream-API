package com.foxminded.senkiv;

import com.foxminded.senkiv.exceptions.RaceApplicationRuntimeException;
import com.foxminded.senkiv.task5.App;
import com.foxminded.senkiv.task5.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {

	@Test
	void fileReader_shouldThrowIllegalArgumentExceptionIfFileWithSuchUriDoesNotExist() throws IOException {
		String actualAddress = "/asd/asd/asd";
		FileReader reader = new FileReader();
		assertThrows(RaceApplicationRuntimeException.class, ()-> reader.createStream(actualAddress));
	}

	@Test
	void fileReader_shouldThrowIllegalArgumentExceptionIfUriIsEmpty() {
		String actualAddress = "   ";
		FileReader reader = new FileReader();
		assertThrows(RaceApplicationRuntimeException.class, () -> reader.createStream(actualAddress));
	}

	@Test
	void fileReader_shouldThrowIllegalArgumentExceptionIfUriIsNull() {
		FileReader reader = new FileReader();
		assertThrows(RaceApplicationRuntimeException.class, () -> reader.createStream(null));
	}

	@Test
	void fileReader_shouldThrowIllegalArgumentExceptionIfFailedToReadTheFile() {
		FileReader reader = new FileReader();
		assertThrows(RaceApplicationRuntimeException.class, () -> reader.createStream("NoUri/NoUri/a"));
	}

	@Test
	void fileReader_shouldReturnStreamIfUriWasValid() {
		String actualAddress = App.START_FILE;
		FileReader reader = new FileReader();
		Stream<String> stream = reader.createStream(actualAddress);
		assertNotNull(stream);
	}
}


