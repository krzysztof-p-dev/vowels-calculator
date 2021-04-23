package com.demo.volvescalculator.launcher;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {"classpath:application.properties"})
public class DataLoader {

    @Value("${path.to.input.file}")
    private String inputFile;

    @Value("${path.to.output.file.folder}")
    private String outputDirectory;

    @Bean
    public String inputFile() {
        return inputFile;
    }

    @Bean
    public String outputDirectory() {
        return outputDirectory;
    }

}
