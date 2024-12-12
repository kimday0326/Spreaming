package dev.spreaming.streaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dev.spreaming"})
public class StreamingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamingApplication.class, args);
	}

}
