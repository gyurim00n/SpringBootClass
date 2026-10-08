package org.doit.ik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Sb09MjtMreview2FileuploadApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb09MjtMreview2FileuploadApplication.class, args);
	}

}
