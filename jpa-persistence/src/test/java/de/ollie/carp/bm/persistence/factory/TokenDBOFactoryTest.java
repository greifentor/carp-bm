package de.ollie.carp.bm.persistence.factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenDBOFactoryTest {

	private static final String NAME = "name";
	private static final UUID UID0 = UUID.randomUUID();
	private static final UUID UID1 = UUID.randomUUID();

	@Mock
	private UUIDFactory uuidFactory;

	@InjectMocks
	private TokenDBOFactory unitUnderTest;

	@Nested
	class TestsOfMethod_createWithName_String {

		@Test
		void returnsANewTokenDBO() {
			// Prepare
			when(uuidFactory.create()).thenReturn(UID0);
			// Run
			assertNotNull(unitUnderTest.createWithName(NAME));
		}

		@Test
		void returnsANewTokenDBOWithSetName() {
			// Prepare
			when(uuidFactory.create()).thenReturn(UID0);
			// Run
			TokenDBO returned = unitUnderTest.createWithName(NAME);
			//
			assertEquals(NAME, returned.getName());
		}

		@Test
		void returnsANewTokenDBOWithSetUUID() {
			// Prepare
			when(uuidFactory.create()).thenReturn(UID0);
			// Run
			TokenDBO returned = unitUnderTest.createWithName(NAME);
			//
			assertEquals(UID0, returned.getId());
		}

		@Test
		void returnsANewTokenDBOOnEachCall() {
			// Prepare
			when(uuidFactory.create()).thenReturn(UID0, UID1);
			// Run
			assertNotEquals(unitUnderTest.createWithName(NAME), unitUnderTest.createWithName(NAME));
		}
	}
}
