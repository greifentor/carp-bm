package de.ollie.carp.bm.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.SpielrundeToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.SpielrundeTokenPersistencePort;
import de.ollie.carp.bm.core.service.port.persistence.TokenPersistencePort;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

	private static final String NAME = "name";
	private static final String STRING = "string";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private Coordinates coordinates;

	@Mock
	private UUIDFactory uuidFactory;

	@Mock
	private Spielrunde sitzung;

	@Mock
	private SpielrundeToken sitzungToken;

	@Mock
	private Token token;

	@Mock
	private SpielrundeTokenPersistencePort sitzungTokenPersistencePort;

	@Mock
	private TokenPersistencePort tokenPersistencePort;

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

	@Nested
	class TestsOfMethod_createTokenWithName_String {

		@Test
		void delegatesToTokenPersistencePortMethodCorrectly() {
			// Prepare
			when(tokenPersistencePort.createTokenWithName(NAME)).thenReturn(token);
			// Run
			Token returned = unitUnderTest.createTokenWithName(NAME);
			// Check
			assertSame(token, returned);
			verify(tokenPersistencePort, times(1)).createTokenWithName(NAME);
			verifyNoMoreInteractions(tokenPersistencePort);
		}
	}

	@Nested
	class TestsOfMethod_delete_String {

		@Test
		void callsTheTokenPersistencePortMethodCorrectly_passingAnUUID() {
			// Prepare
			when(uuidFactory.createFromString(STRING)).thenReturn(UID);
			when(tokenPersistencePort.findByName(STRING)).thenReturn(Optional.empty());
			// Run
			unitUnderTest.delete(STRING);
			// Check
			verify(tokenPersistencePort, times(1)).deleteById(UID);
		}

		@Test
		void callsTheTokenPersistencePortMethodCorrectly_passingAName() {
			// Prepare
			Optional<Token> foundByName = Optional.of(token);
			when(token.getId()).thenReturn(UID);
			when(tokenPersistencePort.findByName(STRING)).thenReturn(foundByName);
			// Run
			unitUnderTest.delete(STRING);
			// Check
			verify(tokenPersistencePort, times(1)).deleteById(UID);
		}
	}

	@Nested
	class TestsOfMethod_findAll {

		@Test
		void delegatesToTokenPersistencePortMethodCorrectly() {
			// Prepare
			List<Token> persistencePortReturn = List.of(token);
			when(tokenPersistencePort.findAll()).thenReturn(persistencePortReturn);
			// Run
			List<Token> returned = unitUnderTest.findAll();
			// Check
			assertSame(persistencePortReturn, returned);
			verify(tokenPersistencePort, times(1)).findAll();
			verifyNoMoreInteractions(tokenPersistencePort);
		}
	}

	@Nested
	class TestsOfMethod_findByName_String {

		@Test
		void callsTheTokenPersistencePortMethodCorrectly() {
			// Prepare
			Optional<Token> expected = Optional.of(token);
			when(tokenPersistencePort.findByName(NAME)).thenReturn(expected);
			// Run
			Optional<Token> returned = unitUnderTest.findByName(NAME);
			// Check
			assertSame(expected, returned);
		}
	}
}
