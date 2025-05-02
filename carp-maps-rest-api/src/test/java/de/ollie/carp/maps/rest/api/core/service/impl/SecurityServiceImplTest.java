package de.ollie.carp.maps.rest.api.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.carp.maps.rest.api.core.configuration.SecurityServiceConfiguration;
import de.ollie.carp.maps.rest.api.core.exception.AuthorizationInvalidException;
import java.util.Set;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SecurityServiceImplTest {

	private static final String SECRET = "top-secret";
	private static final String VALID_TOKEN =
		"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhcHAiOiJjYXJwLWJtIiwidWlkIjoidWlkIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjkxNTE2MjM5MTIyfQ.CI-raSXUHcKxIqs0PnZRXLPop7ipB_oeoT9cndsJq-WN71_5pGX5jMDOrzecIPBJX3AMy26lFtMXmveWoAzJTA";
	private static final String EXPIRED_TOKEN =
		"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhcHAiOiJjYXJwLWJtIiwidWlkIjoidWlkIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjE1MTYyMzkxMjJ9.VOSfDsbjX1WAnb5a_4-2PAoJbwn3IBs586BfiRkLPRXJbjWxotXp1SB-FqSwRnudGMvmARQETkokmbSeAKVAPA";

	@Mock
	private SecurityServiceConfiguration configuration;

	@InjectMocks
	private SecurityServiceImpl unitUnderTest;

	@Nested
	class checkAuthorization_String {

		@Test
		void throwsAnException_passingANullPointer() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.checkAuthorization(null));
		}

		@Test
		void throwsAnException_passingAnAuthorization_notStartingWithBearer() {
			assertThrows(AuthorizationInvalidException.class, () -> unitUnderTest.checkAuthorization(";op"));
		}

		@Test
		void throwsAnException_passingAnInvalidAuthorization_withInvalidSignature() {
			// Prepare
			when(configuration.getSecret()).thenReturn(SECRET + 1);
			// Run & Check
			assertThrows(
				AuthorizationInvalidException.class,
				() -> unitUnderTest.checkAuthorization(SecurityServiceImpl.BEARER_PREFIX + VALID_TOKEN)
			);
		}

		@Test
		void throwsAnException_passingAnInvalidAuthorization_withExpiredToken() {
			// Prepare
			when(configuration.getSecret()).thenReturn(SECRET);
			// Run & Check
			assertThrows(
				AuthorizationInvalidException.class,
				() -> unitUnderTest.checkAuthorization(SecurityServiceImpl.BEARER_PREFIX + EXPIRED_TOKEN)
			);
		}

		@Test
		void throwsAnException_passingAnInvalidAuthorization_withInvalidApplication() {
			// Prepare
			when(configuration.getSecret()).thenReturn(SECRET);
			when(configuration.getValidApplications()).thenReturn(Set.of());
			// Run & Check
			assertThrows(
				AuthorizationInvalidException.class,
				() -> unitUnderTest.checkAuthorization(SecurityServiceImpl.BEARER_PREFIX + VALID_TOKEN)
			);
		}

		@Test
		void throwsAnException_passingAnInvalidAuthorization_withInvalidUid() {
			// Prepare
			when(configuration.getSecret()).thenReturn(SECRET);
			when(configuration.getValidApplications()).thenReturn(Set.of("carp-bm"));
			when(configuration.getValidUserIds()).thenReturn(Set.of());
			// Run & Check
			assertThrows(
				AuthorizationInvalidException.class,
				() -> unitUnderTest.checkAuthorization(SecurityServiceImpl.BEARER_PREFIX + VALID_TOKEN)
			);
		}

		@Test
		void noExceptionThrown_passingAValidAuthorization() {
			// Prepare
			when(configuration.getSecret()).thenReturn(SECRET);
			when(configuration.getValidApplications()).thenReturn(Set.of("carp-bm"));
			when(configuration.getValidUserIds()).thenReturn(Set.of("uid"));
			// Run & Check
			assertDoesNotThrow(() -> unitUnderTest.checkAuthorization(SecurityServiceImpl.BEARER_PREFIX + VALID_TOKEN));
		}
	}
}
