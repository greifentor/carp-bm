package de.ollie.carp.bm.swing;

import java.awt.EventQueue;
import lombok.Generated;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@Generated
@SpringBootApplication
@ComponentScan("de.ollie.carp.bm")
public class CarpBmSwingStarter {

	public static void main(String[] args) {
		var ctx = new SpringApplicationBuilder(CarpBmSwingStarter.class).headless(false).run(args);
		EventQueue.invokeLater(() -> {
			var af = ctx.getBean(ApplicationFrame.class);
			af.setVisible(true);
		});
	}
}
