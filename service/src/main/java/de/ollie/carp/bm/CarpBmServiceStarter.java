package de.ollie.carp.bm;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.bm")
@EnableJpaRepositories(basePackages = { "de.ollie.carp.bm.persistence.repository" })
public class CarpBmServiceStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarpBmServiceStarter.class, args);
	}
}
