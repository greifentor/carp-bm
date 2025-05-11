package de.ollie.carp.maps.rest.api.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SpielwerteParserTest {

	private static final String RK = "$RK_STR$";

	private static final String SPIELWERTE =
		"<b>Rüstungsklasse</b> " +
		RK +
		" - <b>Bewegungsrate</b> 9 m - <b>Herausforderungsgrad</b> 1 (200 EP)\n" +
		"<b>STR</b> +1 &nbsp;&nbsp; <b>GES</b> +2 &nbsp;&nbsp; <b>INT</b> -2 &nbsp;&nbsp; <b>CHA</b> -2\n" +
		"<b>Schadensimmunitäten</b> Gift - <b>Zustandsimmunitäten</b> Bezauber, erschöpft, vergiftet\n" +
		"<b>Sinne</b> Dunkelsicht 18 m, passive Wahrnehmung 10\n";

	@InjectMocks
	private SpielwerteParser unitUnderTest;

	@Nested
	class getRk_String {

		@Test
		void returns10_passingANullValue() {
			assertEquals(10, unitUnderTest.getRk(null));
		}

		@Test
		void returns10_passingAnEmptyString() {
			assertEquals(10, unitUnderTest.getRk(""));
		}

		@ParameterizedTest
		@CsvSource({ "-1", "8", "10", "14", "20" })
		void returnsCorrectRk_havingANumericValueOnly(int rk) {
			// Prepare
			String spielwerte = SPIELWERTE.replace(RK, "" + rk);
			// Run & Check
			assertEquals(rk, unitUnderTest.getRk(spielwerte));
		}

		@Test
		void returnsCorrectRk_havingANumericValueWithRemark() {
			// Prepare
			String spielwerte = SPIELWERTE.replace(RK, "42 (bla)");
			// Run & Check
			assertEquals(42, unitUnderTest.getRk(spielwerte));
		}
	}
}
