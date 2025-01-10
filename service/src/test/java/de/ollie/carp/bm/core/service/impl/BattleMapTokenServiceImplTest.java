package de.ollie.carp.bm.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.service.port.persistence.BattleMapTokenPersistencePort;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BattleMapTokenServiceImplTest {

	private static final UUID UID = UUID.randomUUID();

	@Mock
	private BattleMapToken battleMapToken;

	@Mock
	private BattleMapToken battleMapToken1;

	@Mock
	private BattleMapTokenPersistencePort battleMapTokenPersistencePort;

	@InjectMocks
	private BattleMapTokenServiceImpl unitUnderTest;

	@Nested
	class findById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findById(null));
		}

		@Test
		void returnsTheObjectReturnedByThePersistencePortMethodCall() {
			// Prepare
			Optional<BattleMapToken> expected = Optional.of(battleMapToken);
			when(battleMapTokenPersistencePort.findById(UID)).thenReturn(expected);
			// Run & Check
			assertSame(expected, unitUnderTest.findById(UID));
		}
	}

	@Nested
	class save_BattleMapToken {

		@Test
		void throwsAnException_passingANullValueAsBattleMapToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.save(null));
		}

		@Test
		void returnsTheObjectReturnedByThePersistencePortMethodCall() {
			// Prepare
			when(battleMapTokenPersistencePort.save(battleMapToken)).thenReturn(battleMapToken1);
			// Run & Check
			assertSame(battleMapToken1, unitUnderTest.save(battleMapToken));
		}
	}
}
