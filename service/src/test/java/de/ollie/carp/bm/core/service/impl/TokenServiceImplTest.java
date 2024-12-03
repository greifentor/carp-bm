package de.ollie.carp.bm.core.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Sitzung;
import de.ollie.carp.bm.core.model.SitzungToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.port.persistence.SitzungTokenPersistencePort;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

	@Mock
	private Coordinates coordinates;

	@Mock
	private Sitzung sitzung;

	@Mock
	private SitzungToken sitzungToken;

	@Mock
	private Token token;

	@Mock
	private SitzungTokenPersistencePort sitzungTokenPersistencePort;

	@InjectMocks
	private TokenServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_addTokenToMapOfSitzung_Sitzung_Token_Coordinates {

		@Test
		void delegatesToSitzungTokenPersistencePortMethodCorrectly() {
			// Run
			unitUnderTest.addTokenToMapOfSitzung(sitzung, token, coordinates);
			// Check
			verify(sitzungTokenPersistencePort, times(1)).addTokenToMapOfSitzung(sitzung, token, coordinates);
			verifyNoMoreInteractions(sitzungTokenPersistencePort);
		}
	}
}
