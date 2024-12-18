package de.ollie.carp.bm.core.service.factory.impl;

import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import jakarta.inject.Named;
import java.util.UUID;

@Named
public class UUIDFactoryImpl implements UUIDFactory {

	@Override
	public UUID create() {
		return UUID.randomUUID();
	}

	@Override
	public UUID createFromString(String uuid) {
		// TODO Auto-generated method stub
		return null;
	}
}
