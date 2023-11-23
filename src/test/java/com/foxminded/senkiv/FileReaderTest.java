package com.foxminded.senkiv;

import com.foxminded.senkiv.config.SpringConfiguration;
import com.foxminded.senkiv.exceptions.RaceApplicationRuntimeException;
import com.foxminded.senkiv.task5.App;
import com.foxminded.senkiv.task5.FileReader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes= SpringConfiguration.class)
class FileReaderTest {
	private static FileReader fileReader;

	@BeforeAll
	static void setUp(ApplicationContext context){
		fileReader = context.getBean(FileReader.class);
	}

	@Test
	void fileFileReader_shouldThrowIllegalArgumentExceptionIfFileWithSuchUriDoesNotExist() {
		String actualAddress = "/asd/asd/asd";
		assertThrows(RaceApplicationRuntimeException.class, ()-> fileReader.createStream(actualAddress));
	}

	@Test
	void fileFileReader_shouldThrowIllegalArgumentExceptionIfUriIsEmpty() {
		String actualAddress = "   ";
		assertThrows(RaceApplicationRuntimeException.class, () -> fileReader.createStream(actualAddress));
	}

	@Test
	void fileFileReader_shouldThrowIllegalArgumentExceptionIfUriIsNull() {
		assertThrows(RaceApplicationRuntimeException.class, () -> fileReader.createStream(null));
	}

	@Test
	void fileFileReader_shouldThrowIllegalArgumentExceptionIfFailedToReadTheFile() {
		assertThrows(RaceApplicationRuntimeException.class, () -> fileReader.createStream("NoUri/NoUri/a"));
	}

	@Test
	void fileFileReader_shouldReturnStreamIfUriWasValid() {
		String actualAddress = App.START_FILE;
		Stream<String> stream = fileReader.createStream(actualAddress);
		assertNotNull(stream);
	}
}


