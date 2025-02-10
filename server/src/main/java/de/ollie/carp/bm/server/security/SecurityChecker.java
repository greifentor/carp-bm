package de.ollie.carp.bm.server.security;

public interface SecurityChecker {
	void throwExceptionIfAccessTokenInvalid(String accessToken);
}
