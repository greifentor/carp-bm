package de.ollie.carp.bm.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.core.service.port.persistence.TokenDataPersistencePort;
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
class TokenDataServiceImplTest {

	private static final UUID UID = UUID.randomUUID();

	@Mock
	private TokenData tokenData;

	@Mock
	private TokenData tokenDataReturn;

	@Mock
	private TokenDataPersistencePort persistencePort;

	@Mock
	private UUIDFactory uuidFactory;

	@InjectMocks
	private TokenDataServiceImpl unitUnderTest;

	@Nested
	class create_TokenData {

		@Test
		void throwsAnException_passingANullValueAsTokenData() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(null));
		}

		@Test
		void setsANewIdToThePassedTokenData() {
			// Prepare
			when(uuidFactory.create()).thenReturn(UID);
			// Run
			unitUnderTest.create(tokenData);
			// Check
			verify(tokenData, times(1)).setId(UID);
		}

		@Test
		void returnsTheObject_returnedByThePersistencePortCall() {
			// Prepare
			when(persistencePort.save(tokenData)).thenReturn(tokenDataReturn);
			// Run & Check
			assertSame(tokenDataReturn, unitUnderTest.create(tokenData));
		}
	}

	@Nested
	class deleteById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.deleteById(null));
		}

		@Test
		void throwsAnException_passingAnUnknownId() {
			// Prepare
			when(persistencePort.findById(UID)).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(NoSuchRecordException.class, () -> unitUnderTest.deleteById(UID));
		}

		@Test
		void returnsTheDeletedObject() {
			// Prepare
			when(persistencePort.findById(UID)).thenReturn(Optional.of(tokenDataReturn));
			// Run & Check
			assertSame(tokenDataReturn, unitUnderTest.deleteById(UID));
		}

		@Test
		void callsThePersistencePortCorrectly() {
			// Prepare
			when(persistencePort.findById(UID)).thenReturn(Optional.of(tokenDataReturn));
			// Run
			unitUnderTest.deleteById(UID);
			// Check
			verify(persistencePort, times(1)).deleteById(UID);
		}
	}

	@Nested
	class findAll {

		@Test
		void returnsTheOptional_returnedByThePersistenceMethodCall() {
			// Prepare
			List<TokenData> expected = List.of(tokenDataReturn);
			when(persistencePort.findAll()).thenReturn(expected);
			// Run & Check
			assertSame(expected, unitUnderTest.findAll());
		}
	}

	@Nested
	class findById_UUID {

		@Test
		void returnsTheOptional_returnedByThePersistenceMethodCall() {
			// Prepare
			Optional<TokenData> expected = Optional.of(tokenDataReturn);
			when(persistencePort.findById(UID)).thenReturn(expected);
			// Run & Check
			assertSame(expected, unitUnderTest.findById(UID));
		}
	}
}
