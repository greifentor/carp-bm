package de.ollie.carp.bm.server.security.impl;

import de.ollie.carp.bm.server.security.SecurityChecker;
import jakarta.inject.Named;

@Named
public class SecurityCheckerImpl implements SecurityChecker {

	@Override
	public void throwExceptionIfAccessTokenInvalid(String accessToken) {
		System.out.println("access token checked: " + accessToken);
	}
}
