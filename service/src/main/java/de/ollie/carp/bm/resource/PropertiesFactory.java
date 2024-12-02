package de.ollie.carp.bm.resource;

import jakarta.inject.Named;
import java.util.Properties;

@Named
class PropertiesFactory {

	Properties create() {
		return new Properties();
	}
}
