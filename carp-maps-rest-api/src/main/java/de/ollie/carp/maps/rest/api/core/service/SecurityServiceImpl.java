package de.ollie.carp.maps.rest.api.core.service;

import static de.ollie.carp.bm.util.Check.ensure;

import jakarta.inject.Named;

@Named
class SecurityServiceImpl implements SecurityService {

	static final String BEARER_PREFIX = "Bearer ";

	@Override
	public void checkAuthorization(String bearerToken) {
		ensure(bearerToken != null, "bearer token cannot null");
		ensure(
			bearerToken.startsWith(BEARER_PREFIX),
			() -> new AuthorizationInvalidException("authorization is not a bearer token")
		);
	}
}
