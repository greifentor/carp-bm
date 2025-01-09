package de.ollie.carp.bm.gui.go;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.CoordinatesXY;
import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BattleMapTokenGOTest {

	private static final int FIELD_SIZE_IN_PIXELS = 20;
	private static final BigDecimal FIELD_X = new BigDecimal(2.0);
	private static final BigDecimal FIELD_Y = new BigDecimal(1.0);
	private static final int OFFSET_IN_PIXELS = 10;

	@Mock
	private BattleMapGO battleMap;

	@Mock
	private TokenGO token;

	@InjectMocks
	private BattleMapTokenGO unitUnderTest;

	@Nested
	class getTokenLeftUpperCorner {

		@Test
		void returnsTheCorrectValue() {
			// Prepare
			unitUnderTest.setFieldX(FIELD_X);
			unitUnderTest.setFieldY(FIELD_Y);
			when(battleMap.getFieldSizeInPixels()).thenReturn(FIELD_SIZE_IN_PIXELS);
			when(battleMap.getOffsetInPixels()).thenReturn(OFFSET_IN_PIXELS);
			// Run & Check
			assertEquals(
				new CoordinatesXY()
					.setX((int) (FIELD_SIZE_IN_PIXELS * FIELD_X.doubleValue() + OFFSET_IN_PIXELS))
					.setY((int) (FIELD_SIZE_IN_PIXELS * FIELD_Y.doubleValue() + OFFSET_IN_PIXELS)),
				unitUnderTest.getTokenLeftUpperCorner()
			);
		}
	}
}
