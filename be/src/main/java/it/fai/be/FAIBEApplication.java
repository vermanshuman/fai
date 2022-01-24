package it.fai.be;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FAIBEApplication {

	public static void main(String[] args) {
		SpringApplication.run(FAIBEApplication.class, args);
	}

}
