package com.nisum.evaluacionJava;

import com.nisum.evaluacionJava.config.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(RsaKeyProperties.class)
@SpringBootApplication
public class EvaluacionJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvaluacionJavaApplication.class, args);
	}

}
