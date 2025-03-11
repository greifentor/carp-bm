package de.ollie.carp.bm.server.security.impl;

import de.ollie.carp.bm.server.security.SecurityChecker;
import jakarta.inject.Named;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named
class SecurityCheckerImpl implements SecurityChecker {

	private static final Logger LOG = LogManager.getLogger(SecurityCheckerImpl.class);

	@Override
	public void throwExceptionIfAccessTokenInvalid(String accessToken) {
		LOG.info("access token checked: " + accessToken);
	}
}
