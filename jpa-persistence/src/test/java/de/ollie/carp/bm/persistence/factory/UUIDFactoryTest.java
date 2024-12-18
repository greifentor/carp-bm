package de.ollie.carp.bm.persistence.factory;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UUIDFactoryTest {

	@InjectMocks
	private UUIDFactory unitUnderTest;

	@Test
	void returnsANewUUID() {
		assertNotNull(unitUnderTest.create());
	}

	@Test
	void returnsANewUUIDOnEveryCall() {
		assertNotEquals(unitUnderTest.create(), unitUnderTest.create());
	}
}
