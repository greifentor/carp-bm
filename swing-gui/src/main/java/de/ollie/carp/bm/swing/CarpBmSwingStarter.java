package de.ollie.carp.bm.swing;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.bm")
@EntityScan("de.ollie.carp.bm.persistence.entity")
@EnableJpaRepositories(basePackages = { "de.ollie.carp.bm.persistence.repository" })
public class CarpBmSwingStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarpBmSwingStarter.class, args);
	}
}