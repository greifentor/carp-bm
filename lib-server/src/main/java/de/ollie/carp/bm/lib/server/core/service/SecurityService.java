package de.ollie.carp.bm.lib.server.core.service;

public interface SecurityService {
	void checkAuthorization(String bearerToken);
}
