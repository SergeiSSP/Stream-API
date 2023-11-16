package com.foxminded.senkiv.config;

import com.foxminded.senkiv.task5.AbbreviationsHandler;
import com.foxminded.senkiv.task5.FileReader;
import com.foxminded.senkiv.task5.OutputManager;
import com.foxminded.senkiv.task5.TimeCounter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.IOException;
import java.util.Iterator;
import java.util.stream.Stream;

import static com.foxminded.senkiv.task5.App.*;


@Configuration
public class SpringConfiguration {
    @Bean
	@Scope(value = "prototype")
    public AbbreviationsHandler abbreviationsHandler(){
        return  new AbbreviationsHandler();
    }

    @Bean(name="startReader")
	@Scope(value = "prototype")
    public FileReader startReader(){
        FileReader startReader =  new FileReader();
        startReader.setFileUri(START_FILE);
        return startReader;
    }

    @Bean(name="startStream")
	@Scope(value = "prototype")
    public Stream<String> startStream() throws IOException {
            return startReader().createStream();
    }

	@Bean(name="startIterator")
	@Scope(value = "prototype")
	public Iterator<String> startIterator() throws IOException {
		return startStream().sorted().iterator();
	}

    @Bean(name="endReader")
	@Scope(value = "prototype")
    public FileReader endReader(){
        FileReader endReader = new FileReader();
        endReader.setFileUri(END_FILE);
        return endReader;
    }

    @Bean(name="endStream")
	@Scope(value = "prototype")
    public Stream<String> endStream() throws IOException {
        return endReader().createStream();
    }

	@Bean(name="endIterator")
	@Scope(value = "prototype")
	public Iterator<String> endIterator() throws IOException {
		return endStream().sorted().iterator();
	}

    @Bean(name="abbreviationsReader")
	@Scope(value = "prototype")
    public FileReader abbreviationsReader(){
        FileReader abbreviationsReader = new FileReader();
        abbreviationsReader.setFileUri(ABBREVIATIONS);
        return abbreviationsReader;
    }

    @Bean(name="abbreviationsStream")
	@Scope(value = "prototype")
    public Stream<String> abbreviationsStream() throws IOException {
        return abbreviationsReader().createStream();
    }

    @Bean(name="timeCounter")
	@Scope(value = "prototype")
    public TimeCounter timeCounter(){
        return new TimeCounter();
    }

    @Bean(name="manager")
	@Scope(value = "prototype")
    public OutputManager manager(){
        return new OutputManager();
    }
}
