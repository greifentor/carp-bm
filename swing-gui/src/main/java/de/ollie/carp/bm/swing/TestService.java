package de.ollie.carp.bm.swing;

import de.ollie.carp.bm.client.TokenClient;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class TestService {

	private final TokenClient tokenClient;

	@PostConstruct
	void postConstruct() {
		System.out.println(tokenClient.createTokenWithName("TEST-TOKEN"));
	}
}
