package org.doit.ik;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@MapperScan("org.doit.ik.persistence")
public class Sb02OracleMybatisJspApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb02OracleMybatisJspApplication.class, args);
	}
	
	@Bean
    ApplicationRunner beanList(
            ConfigurableApplicationContext context) {

        return args -> {
            for (String name :
                    context.getBeanDefinitionNames()) {
                System.out.println("😑😑😑" + name);
            }
        };
    }

}
