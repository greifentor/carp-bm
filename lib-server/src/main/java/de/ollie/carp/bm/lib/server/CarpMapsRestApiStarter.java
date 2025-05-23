package de.ollie.carp.bm.lib.server;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.maps.rest.api")
@EntityScan("de.ollie.carp.maps.rest.api.persistence.entity")
@EnableJpaRepositories(basePackages = { "de.ollie.carp.maps.rest.api.persistence.repository" })
public class CarpMapsRestApiStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarpMapsRestApiStarter.class, args);
	}
}
