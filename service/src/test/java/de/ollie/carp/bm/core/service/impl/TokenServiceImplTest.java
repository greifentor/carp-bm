package de.ollie.carp.bm.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapTokenPersistencePort;
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
	private BattleMap battleMap;

	@Mock
	private BattleMapToken battleMapToken;

	@Mock
	private Coordinates coordinates;

	@Mock
	private UUIDFactory uuidFactory;

	@Mock
	private Token token0;

	@Mock
	private Token token1;

	@Mock
	private BattleMapTokenPersistencePort battleMapTokenPersistencePort;

	@Mock
	private TokenPersistencePort persistencePort;

	@InjectMocks
	private TokenServiceImpl unitUnderTest;

	@Nested
	class addTokenToMapOfSitzung_Sitzung_Token_Coordinates {

		@Test
		void delegatesToSitzungTokenPersistencePortMethodCorrectly() {
			// Run
			unitUnderTest.addTokenToBattleMap(token0, battleMap, coordinates);
			// Check
			verify(battleMapTokenPersistencePort, times(1)).addTokenToBattleMap(token0, battleMap, coordinates);
			verifyNoMoreInteractions(battleMapTokenPersistencePort);
		}
	}

	@Nested
	class create_Token {

		@Test
		void delegatesToTokenPersistencePortMethodCorrectly() {
			// Prepare
			when(persistencePort.create(token0)).thenReturn(token1);
			when(uuidFactory.create()).thenReturn(UID);
			// Run
			Token returned = unitUnderTest.create(token0);
			// Check
			assertSame(token1, returned);
			verify(token0, times(1)).setId(UID);
			verify(persistencePort, times(1)).create(token0);
			verifyNoMoreInteractions(persistencePort);
		}
	}

	@Nested
	class delete_String {

		@Test
		void callsTheTokenPersistencePortMethodCorrectly_passingAnUUID() {
			// Prepare
			Optional<Token> foundById = Optional.of(token0);
			when(token0.getId()).thenReturn(UID);
			when(uuidFactory.createFromString(STRING)).thenReturn(UID);
			when(persistencePort.findByName(STRING)).thenReturn(Optional.empty());
			when(persistencePort.findById(UID)).thenReturn(foundById);
			// Run
			Token returned = unitUnderTest.delete(STRING);
			// Check
			assertSame(token0, returned);
			verify(persistencePort, times(1)).deleteById(UID);
		}

		@Test
		void callsTheTokenPersistencePortMethodCorrectly_passingAName() {
			// Prepare
			Optional<Token> foundByName = Optional.of(token0);
			when(token0.getId()).thenReturn(UID);
			when(persistencePort.findByName(STRING)).thenReturn(foundByName);
			// Run
			unitUnderTest.delete(STRING);
			// Check
			verify(persistencePort, times(1)).deleteById(UID);
		}

		@Test
		void throwsAnException_whenFoundNeitherByNameNorById() {
			// Prepare
			when(uuidFactory.createFromString(STRING)).thenReturn(UID);
			when(persistencePort.findById(UID)).thenReturn(Optional.empty());
			when(persistencePort.findByName(STRING)).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(NoSuchRecordException.class, () -> unitUnderTest.delete(STRING));
		}
	}

	@Nested
	class findAll {

		@Test
		void delegatesToTokenPersistencePortMethodCorrectly() {
			// Prepare
			List<Token> persistencePortReturn = List.of(token0);
			when(persistencePort.findAll()).thenReturn(persistencePortReturn);
			// Run
			List<Token> returned = unitUnderTest.findAll();
			// Check
			assertSame(persistencePortReturn, returned);
			verify(persistencePort, times(1)).findAll();
			verifyNoMoreInteractions(persistencePort);
		}
	}

	@Nested
	class findAllByBattleMap_BattleMap {

		@Test
		void happyRun() {
			// Prepare
			List<BattleMapToken> expected = List.of(battleMapToken);
			when(battleMapTokenPersistencePort.findAllByBattleMap(battleMap)).thenReturn(expected);
			// Run
			List<BattleMapToken> returned = unitUnderTest.findAllByBattleMap(battleMap);
			// Check
			assertSame(expected, returned);
		}
	}

	@Nested
	class findByIdOrName_String {

		@Test
		void callsTheTokenPersistencePortMethodCorrectly() {
			// Prepare
			Optional<Token> expected = Optional.of(token0);
			when(persistencePort.findByName(NAME)).thenReturn(expected);
			// Run
			Optional<Token> returned = unitUnderTest.findByIdOrName(NAME);
			// Check
			assertSame(expected, returned);
		}
	}
}
