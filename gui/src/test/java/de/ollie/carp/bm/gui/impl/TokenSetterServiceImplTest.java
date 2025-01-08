package de.ollie.carp.bm.gui.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenSetterServiceImplTest {

	private static final int FIELD_SIZE_IN_PIXELS = 50;
	private static final BigDecimal FIELD_X = new BigDecimal(1.0);
	private static final BigDecimal FIELD_Y = new BigDecimal(2.5);
	private static final int OFFSET_IN_PIXELS = 12;
	private static final int X = 62;
	private static final int Y = 137;

	@Mock
	private BattleMap battleMap;

	@Mock
	private BattleMapToken battleMapToken;

	@Mock
	private ImageIconFactory imageIconFactory;

	private byte[] imageContent = new byte[42];

	@Mock
	private Graphics2D graphics;

	@Mock
	private BufferedImage image;

	@Mock
	private Token token;

	@InjectMocks
	private TokenSetterServiceImpl unitUnderTest;

	@Nested
	class TestsOfMethod_setTokenToBattleMap_BattleMapToken_Graphics2D {

		@Test
		void throwsAnException_passingBattleMapTokenAsNullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.setTokenToBattleMap(null, graphics));
		}

		@Test
		void throwsAnException_passingGraphicsAsNullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.setTokenToBattleMap(battleMapToken, null));
		}

		@Test
		void setsTheCoordinatesCorrectly() {
			// Prepare
			when(battleMapToken.getBattleMap()).thenReturn(battleMap);
			when(battleMapToken.getToken()).thenReturn(token);
			when(battleMapToken.getFieldX()).thenReturn(FIELD_X);
			when(battleMapToken.getFieldY()).thenReturn(FIELD_Y);
			when(battleMap.getFieldSizeInPixels()).thenReturn(FIELD_SIZE_IN_PIXELS);
			when(battleMap.getOffsetInPixels()).thenReturn(OFFSET_IN_PIXELS);
			when(imageIconFactory.create(imageContent)).thenReturn(image);
			when(token.getImage()).thenReturn(imageContent);
			// Run
			unitUnderTest.setTokenToBattleMap(battleMapToken, graphics);
			// Check
			verify(graphics, times(1)).drawImage(image, X, Y, null);
		}
	}
}
