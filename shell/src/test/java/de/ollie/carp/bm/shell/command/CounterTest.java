package de.ollie.carp.bm.shell.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CounterTest {

	@InjectMocks
	private Counter unitUnderTest;

	@Nested
	class inc {

		@Test
		void incrementsTheCounterByOne() {
			assertEquals(1, unitUnderTest.inc().getValue());
		}
	}

	@Nested
	class getValue {

		@Test
		void returnsZero_whenInitialized() {
			assertEquals(0, unitUnderTest.getValue());
		}
	}
}
