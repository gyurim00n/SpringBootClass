package org.doit.ik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Sb11BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb11BlogApplication.class, args);
	}

}
