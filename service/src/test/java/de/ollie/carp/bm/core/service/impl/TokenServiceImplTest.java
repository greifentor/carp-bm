package de.ollie.carp.bm.core.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.SpielrundeToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.port.persistence.SpielrundeTokenPersistencePort;
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
	private Spielrunde sitzung;

	@Mock
	private SpielrundeToken sitzungToken;

	@Mock
	private Token token;

	@Mock
	private SpielrundeTokenPersistencePort sitzungTokenPersistencePort;

	@InjectMocks
	private TokenServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_addTokenToMapOfSitzung_Sitzung_Token_Coordinates {

		@Test
		void delegatesToSitzungTokenPersistencePortMethodCorrectly() {
			// Run
			unitUnderTest.addTokenToMapOfSitzung(sitzung, token, coordinates);
			// Check
			verify(sitzungTokenPersistencePort, times(1)).addTokenToMapOfSpielrunde(sitzung, token, coordinates);
			verifyNoMoreInteractions(sitzungTokenPersistencePort);
		}
	}
}
