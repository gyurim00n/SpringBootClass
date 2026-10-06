package org.doit.ik;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing	//regDate, modDate 적절한 값 설정시키는  AuditingEntityListener 활성화 역할 어노테이션  
public class Sb07MjtGuestbook2CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sb07MjtGuestbook2CrudApplication.class, args);
	}

}
