package com.foxminded.senkiv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {
    private String fileUri;

    public FileReader(String fileUri){
        this.fileUri = fileUri;
    }

    public Stream<String> createStream(){
        Path path = Paths.get(fileUri);
        Stream<String> lines = null;
        try {
            lines = Files.lines(path);
        }catch(IOException e){
            e.printStackTrace();
        }
        return lines;
    }
}
