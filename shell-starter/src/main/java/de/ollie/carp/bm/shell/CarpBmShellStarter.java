package de.ollie.carp.bm.shell;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.bm")
public class CarpBmShellStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarpBmShellStarter.class, args);
	}
}
