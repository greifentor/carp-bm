package de.ollie.carp.bm.swing;

import java.awt.EventQueue;
import lombok.Generated;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.bm")
@EntityScan("de.ollie.carp.bm.persistence.entity")
@EnableJpaRepositories(basePackages = { "de.ollie.carp.bm.persistence.repository" })
public class CarpBmSwingAndRestServerStarter {

	public static void main(String[] args) {
		var ctx = new SpringApplicationBuilder(CarpBmSwingAndRestServerStarter.class).headless(false).run(args);
		EventQueue.invokeLater(() -> {
			var af = ctx.getBean(ApplicationFrame.class);
			af.setVisible(true);
		});
	}
}
