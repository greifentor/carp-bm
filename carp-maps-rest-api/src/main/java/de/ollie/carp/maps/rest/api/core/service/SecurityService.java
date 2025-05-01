package de.ollie.carp.maps.rest.api.core.service;

public interface SecurityService {
	void checkAuthorization(String bearerToken);
}
