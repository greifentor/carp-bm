package de.ollie.carp.bm.persistence.factory;

import jakarta.inject.Named;
import java.util.UUID;

@Named
public class UUIDFactory {

	public UUID create() {
		return UUID.randomUUID();
	}
}
