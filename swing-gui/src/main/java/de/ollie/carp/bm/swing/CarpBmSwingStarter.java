package de.ollie.carp.bm.swing;

import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.bm")
public class CarpBmSwingStarter {

	public static void main(String[] args) {
		SpringApplication.run(CarpBmSwingStarter.class, args);
	}
}
