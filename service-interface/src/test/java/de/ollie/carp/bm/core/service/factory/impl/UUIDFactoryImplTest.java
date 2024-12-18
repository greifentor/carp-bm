package de.ollie.carp.bm.core.service.factory.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UUIDFactoryImplTest {

	private static final UUID UID = UUID.randomUUID();

	@InjectMocks
	private UUIDFactoryImpl unitUnderTest;

	@Nested
	class TestsOfMethod_create {

		@Test
		void returnsAnUUID() {
			assertNotNull(unitUnderTest.create());
		}

		@Test
		void returnsANewUUID_onEachCall() {
			assertNotSame(unitUnderTest.create(), unitUnderTest.create());
		}
	}

	@Nested
	class TestsOfMethod_createFromString_String {

		@Test
		void returnsAnUUID_withThePassedString() {
			assertEquals(UID, unitUnderTest.createFromString(UID.toString()));
		}
	}
}
