package com.foxminded.senkiv.task5;

import com.foxminded.senkiv.exceptions.RaceApplicationRuntimeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static com.foxminded.senkiv.task5.StringValidator.validateInputString;

public class FileReader {
	private FileReader(){}

	public static Stream<String> createStream(String uri){
		validateInputString(uri);
		Path path = Paths.get(uri);
		try{
			return Files.lines(path);
		}catch(IOException e){
			throw new RaceApplicationRuntimeException("Failed to read the file.", e.getCause());
		}
	}
}
