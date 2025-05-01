package de.ollie.carp.maps.rest.api.core.service;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SecurityServiceImplTest {

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
	}
}
