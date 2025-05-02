package de.ollie.carp.maps.rest.api.core.service.impl;

import static de.ollie.carp.bm.util.Check.ensure;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import de.ollie.carp.maps.rest.api.core.configuration.SecurityServiceConfiguration;
import de.ollie.carp.maps.rest.api.core.exception.AuthorizationInvalidException;
import de.ollie.carp.maps.rest.api.core.service.SecurityService;
import jakarta.inject.Named;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
class SecurityServiceImpl implements SecurityService {

	private static final String CLAIM_ID_APP = "app";
	private static final String CLAIM_ID_UID = "uid";

	static final String BEARER_PREFIX = "Bearer ";

	private final SecurityServiceConfiguration configuration;

	@Override
	public void checkAuthorization(String bearerToken) {
		ensure(bearerToken != null, "bearer token cannot null");
		ensure(
			bearerToken.startsWith(BEARER_PREFIX),
			() -> new AuthorizationInvalidException("authorization is not a bearer token.")
		);
		String token = bearerToken.replace(BEARER_PREFIX, "");
		verifyJWT(token);
		DecodedJWT decodedJWT = decodeJWT(token);
		verifyClaims(decodedJWT);
	}

	private void verifyJWT(String jwt) {
		Algorithm algorithm = Algorithm.HMAC512(configuration.getSecret());
		JWTVerifier verifier = JWT.require(algorithm).build();
		try {
			verifier.verify(jwt);
		} catch (Exception e) {
			throw new AuthorizationInvalidException("authorization invalid.");
		}
	}

	private DecodedJWT decodeJWT(String jwt) {
		return JWT.decode(jwt);
	}

	private void verifyClaims(DecodedJWT decodedJWT) {
		checkClaimApp(decodedJWT);
		checkClaimUid(decodedJWT);
	}

	private void checkClaimApp(DecodedJWT decodedJWT) {
		try {
			String app = decodedJWT.getClaims().get(CLAIM_ID_APP).asString();
			ensure(configuration.getValidApplications().contains(app), new NoSuchElementException());
		} catch (Exception e) {
			throw new AuthorizationInvalidException("authorization application invalid.");
		}
	}

	private void checkClaimUid(DecodedJWT decodedJWT) {
		try {
			String app = decodedJWT.getClaims().get(CLAIM_ID_UID).asString();
			ensure(configuration.getValidUserIds().contains(app), new NoSuchElementException());
		} catch (Exception e) {
			throw new AuthorizationInvalidException("authorization user invalid.");
		}
	}
}
