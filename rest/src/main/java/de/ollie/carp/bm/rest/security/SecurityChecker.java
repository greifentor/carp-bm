package de.ollie.carp.bm.rest.security;

public interface SecurityChecker {
	void throwExceptionIfAccessTokenInvalid(String accessToken);
}
