package de.ollie.carp.bm.core.service.port;

import de.ollie.carp.bm.core.model.Localization;

public interface ResourcePort {
	String getResourceById(String resourceId, Localization localization);
}
