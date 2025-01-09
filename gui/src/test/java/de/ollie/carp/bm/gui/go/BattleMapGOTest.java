package de.ollie.carp.bm.gui.go;

import static org.junit.jupiter.api.Assertions.assertEquals;

import de.ollie.carp.bm.core.model.Coordinates;
import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class BattleMapGOTest {

	@InjectMocks
	private BattleMapGO unitUnderTest;

	@Nested
	class getFieldCoordinates_int_int {

		@ParameterizedTest
		@CsvSource({ "20,10,31,11,1.0,0.0", "20,10,42,52,1.0,2.0" })
		void returnsCorrectCoordinates_forPassedValues(
			int fieldSizeInPixels,
			int offsetInPixels,
			int x,
			int y,
			BigDecimal expectedX,
			BigDecimal expectedY
		) {
			// Prepare
			Coordinates expected = new Coordinates().setFieldX(expectedX).setFieldY(expectedY);
			unitUnderTest.setFieldSizeInPixels(fieldSizeInPixels);
			unitUnderTest.setOffsetInPixels(offsetInPixels);
			// Run
			Coordinates returned = unitUnderTest.getFieldCoordinates(x, y);
			// Check
			assertEquals(expected, returned);
		}
	}
}
