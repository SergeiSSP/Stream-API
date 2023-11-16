package com.foxminded.senkiv.task5;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    private String fileUri;

    public void setFileUri(String uri){
		validateUri(uri);
        this.fileUri = uri;
    }

	public String getFileUri(){
		return this.fileUri;
	}

    public Stream<String> createStream() throws IOException {
		Stream<String> stream;
		try {
			Path path = Paths.get(fileUri);
			stream = Files.lines(path);
		}catch(NoSuchFileException e){
			throw new NoSuchFileException("This file does not exist.");
		}
        return stream;
    }

    private static void validateUri(String uri){
        if(uri.trim().isEmpty()){
            throw new IllegalArgumentException("Path to file is empty.");
        }
    }
}
