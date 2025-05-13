package de.ollie.carp.maps.rest.api.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.ollie.carp.maps.rest.api.core.model.DnDTokenSize;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SubHeaderParserTest {

	@InjectMocks
	private SubHeaderParser unitUnderTest;

	@Nested
	class getTokenSize_String {

		@Test
		void returnsTokenSizeMITTEL_passingANullValue() {
			assertEquals(DnDTokenSize.MITTEL, unitUnderTest.getTokenSize(null));
		}

		@Test
		void returnsTokenSizeMITTEL_passingAnEmptyString() {
			assertEquals(DnDTokenSize.MITTEL, unitUnderTest.getTokenSize(""));
		}

		@ParameterizedTest
		@CsvSource(
			{ "Mittelgroßer Untoter,MITTEL", "Riesiger Drache,RIESIG", "Riesige Pflanze,RIESIG", "Großer Popanz,GROSS" }
		)
		void returnsTheCorrectTokenSize_passingDifferentStrings(String s, DnDTokenSize expected) {
			assertEquals(expected, unitUnderTest.getTokenSize(s));
		}
	}
}
