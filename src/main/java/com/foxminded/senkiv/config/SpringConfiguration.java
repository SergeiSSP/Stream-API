package com.foxminded.senkiv.config;

import com.foxminded.senkiv.task5.AbbreviationsHandler;
import com.foxminded.senkiv.task5.FileReader;
import com.foxminded.senkiv.task5.OutputManager;
import com.foxminded.senkiv.task5.TimeCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {
	@Bean
	public FileReader fileReader(){
		return new FileReader();
	}
    @Bean
    public AbbreviationsHandler abbreviationsHandler(){
        return  new AbbreviationsHandler();
    }

    @Bean(name="timeCounter")
    public TimeCounter timeCounter(){
        return new TimeCounter();
    }

    @Bean(name="manager")
    public OutputManager manager(){
        return new OutputManager();
    }
}
