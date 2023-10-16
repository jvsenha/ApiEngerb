package br.com.api.apiengerb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "br.com.api.apiengerb.repositorio")
public class ApiengerbApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiengerbApplication.class, args);
	}

}
