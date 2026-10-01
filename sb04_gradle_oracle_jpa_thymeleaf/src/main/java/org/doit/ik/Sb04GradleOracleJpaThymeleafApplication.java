package org.doit.ik;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sb04GradleOracleJpaThymeleafApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb04GradleOracleJpaThymeleafApplication.class, args);
	}

	@Bean
    ApplicationRunner beanList(
            ConfigurableApplicationContext context) {

        return args -> {
            for (String name :
                    context.getBeanDefinitionNames()) {
                System.out.println("¤·¤·¤·" + name);
            }
        };
    }
}
