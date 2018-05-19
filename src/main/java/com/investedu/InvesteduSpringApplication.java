package com.investedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
/*scanBasePackages= {"com.investedu.repositories", "com.investedu.services", "com.investedu.services"}*/

@SpringBootApplication()
@EnableJpaRepositories("com.investedu.repositories")
public class InvesteduSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvesteduSpringApplication.class, args);
	}
}
