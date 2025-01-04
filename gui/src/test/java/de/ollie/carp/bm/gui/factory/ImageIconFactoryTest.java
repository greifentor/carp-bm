package de.ollie.carp.bm.gui.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ImageIconFactoryTest {

	private byte[] imageContent = new byte[42];

	@InjectMocks
	private ImageIconFactory unitUnderTest;

	@Nested
	class TestsOfMethod_create {

		@Test
		void returnsANewImageIcon() {
			assertNotNull(unitUnderTest.create(imageContent));
		}

		@Test
		void returnsANewImageIcon_onEachCall() {
			assertNotSame(unitUnderTest.create(imageContent), unitUnderTest.create(imageContent));
		}
	}
}
