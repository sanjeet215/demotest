package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import com.example.demo.configuration.FileStorageProperties;


@EnableCaching
@SpringBootApplication
@EnableConfigurationProperties({FileStorageProperties.class})
@EnableAsync
public class DemotestApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemotestApplication.class, args);
	}

}
