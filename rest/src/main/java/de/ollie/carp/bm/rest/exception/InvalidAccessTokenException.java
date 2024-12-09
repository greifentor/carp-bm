package de.ollie.carp.bm.rest.exception;

public class InvalidAccessTokenException extends RuntimeException {

	public InvalidAccessTokenException(String invalidToken) {
		super("passed token is invalid: " + invalidToken);
	}
}
