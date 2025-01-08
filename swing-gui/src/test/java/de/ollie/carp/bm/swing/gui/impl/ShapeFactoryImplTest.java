package de.ollie.carp.bm.swing.gui.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.CoordinatesXY;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ShapeFactoryImplTest {

	private static final int HEIGHT = 10;
	private static final int LEFT_UPPER_CORNER_X = 42;
	private static final int LEFT_UPPER_CORNER_Y = 43;
	private static final int WIDTH = 20;

	@Mock
	private BufferedImage image;

	@Mock
	private CoordinatesXY leftUpperCorner;

	@InjectMocks
	private ShapeFactoryImpl unitUnderTest;

	@Nested
	class TestsOfMethod_create_CoordinatesXY_BufferedImage {

		@Test
		void throwsAnException_passingANullValueAsCoordinatesXY() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(null, image));
		}

		@Test
		void throwsAnException_passingANullValueAsBufferedImage() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(leftUpperCorner, null));
		}

		@Test
		void returnsAShape() {
			// Prepare
			trainMocks();
			// Run & Check
			assertNotNull(unitUnderTest.create(leftUpperCorner, image));
		}

		private void trainMocks() {
			when(image.getHeight()).thenReturn(HEIGHT);
			when(leftUpperCorner.getX()).thenReturn(LEFT_UPPER_CORNER_X);
			when(leftUpperCorner.getY()).thenReturn(LEFT_UPPER_CORNER_Y);
			when(image.getWidth()).thenReturn(WIDTH);
		}

		@Test
		void returnsDifferentShapesOnDifferentCalls() {
			// Prepare
			trainMocks();
			// Run & Check
			assertNotSame(unitUnderTest.create(leftUpperCorner, image), unitUnderTest.create(leftUpperCorner, image));
		}

		@Test
		void returnsAShapeWithCorrectValues() {
			// Prepare
			trainMocks();
			// Run & Check
			assertEquals(
				new Rectangle(LEFT_UPPER_CORNER_X, LEFT_UPPER_CORNER_Y, WIDTH, HEIGHT),
				unitUnderTest.create(leftUpperCorner, image).getBounds()
			);
		}
	}
}
