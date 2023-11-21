package com.foxminded.senkiv.task5;

import com.foxminded.senkiv.exceptions.RaceApplicationRuntimeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.Stream;

import static com.foxminded.senkiv.task5.StringValidator.validateInputString;

public class FileReader {
    public Stream<String> createStream(String fileUri){
		validateInputString(fileUri);
		Path path = Paths.get(fileUri);
		Stream<String> stream;
		try{
			stream =  Files.lines(path);
		}catch(IOException e){
			throw new RaceApplicationRuntimeException("Failed to read the file.", e.getCause());
		}
		return stream;
    }

	public Iterator<String> createIterator(String fileUri) {
		Stream<String> stream = createStream(fileUri);
		return stream.sorted().iterator();
	}
}
