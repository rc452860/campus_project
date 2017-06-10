package com.sakura.dev;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableAutoConfiguration
@EnableCaching
public class CampusProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusProjectApplication.class, args);
	}

	@Component
	class MyRunner implements CommandLineRunner {
		Logger logger = LoggerFactory.getLogger(this.getClass());
		public void run(String... strings) throws Exception {
			logger.info("MyRunner");
		}
	}
}

