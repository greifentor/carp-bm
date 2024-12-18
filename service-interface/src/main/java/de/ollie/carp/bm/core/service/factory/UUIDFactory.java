package de.ollie.carp.bm.core.service.factory;

import java.util.UUID;

public interface UUIDFactory {
	UUID create();

	UUID createFromString(String uuid);
}
