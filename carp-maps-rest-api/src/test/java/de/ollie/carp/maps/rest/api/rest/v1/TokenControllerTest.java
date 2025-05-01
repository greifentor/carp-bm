package de.ollie.carp.maps.rest.api.rest.v1;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.ollie.carp.maps.rest.api.core.service.SecurityService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenControllerTest {

	private static final String BEARER = "Bearer";
	private static final int MAX_RECORDS_PER_PAGE = 42;
	private static final int PAGE = 1701;
	private static final String TOKEN = "token";

	private static final String AUTHORIZATION = BEARER + " " + TOKEN;

	@Mock
	private SecurityService securityService;

	@InjectMocks
	private TokenController unitUnderTest;

	@Nested
	class getTokens_String_int_int {

		@Test
		void callsTheSecurityService_withThePassedAuthorizationToken() {
			// Run
			unitUnderTest.getTokens(AUTHORIZATION, PAGE, MAX_RECORDS_PER_PAGE);
			// Check
			verify(securityService, times(1)).checkAuthorization(AUTHORIZATION);
		}
	}
}
