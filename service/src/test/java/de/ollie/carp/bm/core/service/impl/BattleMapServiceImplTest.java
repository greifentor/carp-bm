package de.ollie.carp.bm.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapPersistencePort;
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
public class BattleMapServiceImplTest {

	private static final String NAME = "name";
	private static final String STRING = "string";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private UUIDFactory uuidFactory;

	@Mock
	private BattleMap battleMap0;

	@Mock
	private BattleMap battleMap1;

	@Mock
	private BattleMapPersistencePort persistencePort;

	@InjectMocks
	private BattleMapServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_create_BattleMap {

		@Test
		void delegatesToTokenPersistencePortMethodCorrectly() {
			// Prepare
			when(persistencePort.create(battleMap0)).thenReturn(battleMap1);
			when(uuidFactory.create()).thenReturn(UID);
			// Run
			BattleMap returned = unitUnderTest.create(battleMap0);
			// Check
			assertSame(battleMap1, returned);
			verify(battleMap0, times(1)).setId(UID);
			verify(persistencePort, times(1)).create(battleMap0);
			verifyNoMoreInteractions(persistencePort);
		}
	}

	@Nested
	class TestsOfMethod_delete_String {

		@Test
		void callsTheTokenPersistencePortMethodCorrectly_passingAnUUID() {
			// Prepare
			when(uuidFactory.createFromString(STRING)).thenReturn(UID);
			when(persistencePort.findByName(STRING)).thenReturn(Optional.empty());
			// Run
			unitUnderTest.delete(STRING);
			// Check
			verify(persistencePort, times(1)).deleteById(UID);
		}

		@Test
		void callsTheTokenPersistencePortMethodCorrectly_passingAName() {
			// Prepare
			Optional<BattleMap> foundByName = Optional.of(battleMap0);
			when(battleMap0.getId()).thenReturn(UID);
			when(persistencePort.findByName(STRING)).thenReturn(foundByName);
			// Run
			unitUnderTest.delete(STRING);
			// Check
			verify(persistencePort, times(1)).deleteById(UID);
		}
	}

	@Nested
	class TestsOfMethod_findAll {

		@Test
		void delegatesToTokenPersistencePortMethodCorrectly() {
			// Prepare
			List<BattleMap> persistencePortReturn = List.of(battleMap0);
			when(persistencePort.findAll()).thenReturn(persistencePortReturn);
			// Run
			List<BattleMap> returned = unitUnderTest.findAll();
			// Check
			assertSame(persistencePortReturn, returned);
			verify(persistencePort, times(1)).findAll();
			verifyNoMoreInteractions(persistencePort);
		}
	}

	@Nested
	class TestsOfMethod_findByName_String {

		@Test
		void callsTheTokenPersistencePortMethodCorrectly() {
			// Prepare
			Optional<BattleMap> expected = Optional.of(battleMap0);
			when(persistencePort.findByName(NAME)).thenReturn(expected);
			// Run
			Optional<BattleMap> returned = unitUnderTest.findByName(NAME);
			// Check
			assertSame(expected, returned);
		}
	}
}
