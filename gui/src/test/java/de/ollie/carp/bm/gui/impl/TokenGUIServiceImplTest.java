package de.ollie.carp.bm.gui.impl;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.CoordinatesXY;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import de.ollie.carp.bm.gui.factory.ShapeFactory;
import de.ollie.carp.bm.gui.go.BattleMapGO;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import de.ollie.carp.bm.gui.go.TokenGO;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenGUIServiceImplTest {

	private static final int FIELD_SIZE_IN_PIXELS = 50;
	private static final BigDecimal FIELD_X = new BigDecimal(1.0);
	private static final BigDecimal FIELD_Y = new BigDecimal(2.5);
	private static final int OFFSET_IN_PIXELS = 12;
	private static final int X = 62;
	private static final int Y = 137;

	private static final int MOUSE_X = X + 1;
	private static final int MOUSE_Y = Y + 1;

	@Mock
	private BattleMapGO battleMap;

	@Mock
	private BattleMapTokenGO battleMapToken;

	@Mock
	private CoordinatesXY coordinates;

	@Mock
	private ImageIconFactory imageIconFactory;

	private byte[] imageContent = new byte[42];

	@Mock
	private Graphics2D graphics;

	@Mock
	private BufferedImage image;

	@Mock
	private Shape shape;

	@Mock
	private ShapeFactory shapeFactory;

	@Mock
	private TokenGO token;

	@InjectMocks
	private TokenGUIServiceImpl unitUnderTest;

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
			when(battleMapToken.getTokenLeftUpperCorner()).thenReturn(coordinates);
			when(battleMapToken.getToken()).thenReturn(token);
			when(coordinates.getX()).thenReturn(X);
			when(coordinates.getY()).thenReturn(Y);
			when(token.getImage()).thenReturn(image);
			// Run
			unitUnderTest.setTokenToBattleMap(battleMapToken, graphics);
			// Check
			verify(graphics, times(1)).drawImage(image, X, Y, null);
		}
	}

	@Nested
	class TestsOfMethod_isHit_BattleMapToken_int_int {

		@Test
		void throwsAnException_passingANullValueAsBattleMapToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.isHit(null, MOUSE_X, MOUSE_Y));
		}

		@Test
		void returnsTrue_passingABattleMapTokenWhichIsHit_andHitPixelIsNotTransparent() {
			// Prepare
			when(battleMapToken.getToken()).thenReturn(token);
			when(battleMapToken.getTokenLeftUpperCorner()).thenReturn(coordinates);
			when(coordinates.getX()).thenReturn(X);
			when(coordinates.getY()).thenReturn(Y);
			when(image.getRGB(MOUSE_X - X, MOUSE_Y - Y)).thenReturn(TokenGUIServiceImpl.TRANSPARENT + 1);
			when(shape.contains(MOUSE_X, MOUSE_Y)).thenReturn(true);
			when(shapeFactory.create(coordinates, image)).thenReturn(shape);
			when(token.getImage()).thenReturn(image);
			// Run & Check
			assertTrue(unitUnderTest.isHit(battleMapToken, MOUSE_X, MOUSE_Y));
		}

		@Test
		void returnsFalse_passingABattleMapTokenWhichIsHit_butHitPixelIsTransparent() {
			// Prepare
			when(battleMapToken.getToken()).thenReturn(token);
			when(battleMapToken.getTokenLeftUpperCorner()).thenReturn(coordinates);
			when(coordinates.getX()).thenReturn(X);
			when(coordinates.getY()).thenReturn(Y);
			when(image.getRGB(MOUSE_X - X, MOUSE_Y - Y)).thenReturn(TokenGUIServiceImpl.TRANSPARENT);
			when(shape.contains(MOUSE_X, MOUSE_Y)).thenReturn(true);
			when(shapeFactory.create(coordinates, image)).thenReturn(shape);
			when(token.getImage()).thenReturn(image);
			// Run & Check
			assertFalse(unitUnderTest.isHit(battleMapToken, MOUSE_X, MOUSE_Y));
		}

		@Test
		void returnsFalse_passingABattleMapTokenWhichIsNotHit() {
			// Prepare
			when(battleMapToken.getToken()).thenReturn(token);
			when(battleMapToken.getTokenLeftUpperCorner()).thenReturn(coordinates);
			when(shape.contains(MOUSE_X, MOUSE_Y)).thenReturn(false);
			when(shapeFactory.create(coordinates, image)).thenReturn(shape);
			when(token.getImage()).thenReturn(image);
			// Run & Check
			assertFalse(unitUnderTest.isHit(battleMapToken, MOUSE_X, MOUSE_Y));
		}
	}
}
