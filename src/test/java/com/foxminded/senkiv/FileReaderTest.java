package com.foxminded.senkiv;

import com.foxminded.senkiv.task5.App;
import com.foxminded.senkiv.task5.FileReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderTest {
	@Test
	void fileReader_shouldSetFileUriCorrectlyAndReturnSameValue(){
		String actualAddress = App.START_FILE;
		FileReader reader = new FileReader();
		reader.setFileUri(actualAddress);
		assertEquals(actualAddress, reader.getFileUri());
	}

	@Test
	void fileReader_shouldThrowIllegalArgumentExceptionIfFileWithSuchUriDoesNotExist(){
		String actualAddress = "/asd/asd/asd";
		FileReader reader = new FileReader();
		reader.setFileUri(actualAddress);
		assertThrows(NoSuchFileException.class, reader::createStream);
	}

	@Test
	void fileReader_shouldThrowIllegalArgumentExceptionIfUriIsEmpty(){
		String actualAddress = "   ";
		FileReader reader = new FileReader();
		assertThrows(IllegalArgumentException.class, () -> reader.setFileUri(actualAddress));
	}

	@Test
	void fileReader_shouldReturnStreamIfUriWasValid() throws IOException {
		String actualAddress = App.START_FILE;
		FileReader reader = new FileReader();
		reader.setFileUri(actualAddress);
        assertNotNull(reader.createStream());
	}
}
