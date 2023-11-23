package com.foxminded.senkiv.config;

import com.foxminded.senkiv.task5.OutputManager;
import com.foxminded.senkiv.task5.TimeCounter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfiguration {

    @Bean(name="timeCounter")
    public TimeCounter timeCounter(){
        return new TimeCounter();
    }

    @Bean(name="manager")
    public OutputManager manager(){
        return new OutputManager();
    }
}
