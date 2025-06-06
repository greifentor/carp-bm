package de.ollie.carp.bm.swing.component;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.client.v1.TokenClient;
import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.gui.TokenGUIService;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import de.ollie.carp.bm.gui.go.BattleMapGO;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import de.ollie.carp.bm.gui.go.CoordinatesGO;
import de.ollie.carp.bm.gui.go.HitsGO;
import de.ollie.carp.bm.gui.mapper.BattleMapTokenGOMapper;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.ImageIcon;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class BattleMapImageTest {

	private static final int FIELD_X = 1;
	private static final BigDecimal FIELD_X_BD = new BigDecimal(FIELD_X);
	private static final int FIELD_Y = 2;
	private static final BigDecimal FIELD_Y_BD = new BigDecimal(FIELD_Y);
	private static final String NAME = "name";
	private static final int X = 3;
	private static final int Y = 4;

	@Mock
	private BattleMapGO battleMap;

	@Mock
	private BattleMapTokenDTO battleMapTokenDTO;

	@Mock
	private BattleMapTokenGO battleMapTokenGO;

	@Mock
	private List<BattleMapTokenGO> battleMapTokens;

	@Mock
	private CoordinatesGO coordinates;

	@Mock
	private RuntimeException exception;

	@Mock
	private BattleMapImage.Listener listener0;

	@Mock
	private BattleMapImage.Listener listener1;

	@Mock
	private BattleMapTokenGOMapper battleMapTokenGOMapper;

	@Mock
	private ImageIconFactory imageIconFactory;

	@Mock
	private Logger log;

	@Mock
	private TokenClient tokenClient;

	@Mock
	private TokenGUIService tokenGUIService;

	@InjectMocks
	private BattleMapImage unitUnderTest;

	@Nested
	class addListener_Listener {

		@Test
		void doesNotThrowAnyException_passingANullValue() {
			assertDoesNotThrow(() -> unitUnderTest.addListener(null));
		}
	}

	@Nested
	class fireEvent_int_int_ListTokenGO {

		@Test
		void listenersAreCalledCorrectly_whenEventFired() {
			// Prepare
			HitsGO passed = new HitsGO().setBattleMapTokens(battleMapTokens).setFieldX(FIELD_X).setFieldY(FIELD_Y);
			unitUnderTest.addListener(listener0);
			unitUnderTest.addListener(listener1);
			// Run
			unitUnderTest.fireEvent(FIELD_X, FIELD_Y, battleMapTokens);
			// Check
			verify(listener0, times(1)).hitsDetected(passed);
			verify(listener1, times(1)).hitsDetected(passed);
		}

		@Test
		void listenersAreCalledCorrectly_whenListenerBeforeCausesAnException() {
			// Prepare
			HitsGO passed = new HitsGO().setBattleMapTokens(battleMapTokens).setFieldX(FIELD_X).setFieldY(FIELD_Y);
			unitUnderTest.addListener(listener0);
			unitUnderTest.addListener(listener1);
			doThrow(exception).when(listener0).hitsDetected(passed);
			// Run
			unitUnderTest.fireEvent(FIELD_X, FIELD_Y, battleMapTokens);
			// Check
			verify(listener1, times(1)).hitsDetected(passed);
		}

		@Test
		void stackTraceIsPrintedToConsole_whenAnExceptionOccursWhileCallingAListener() {
			// Prepare
			HitsGO passed = new HitsGO().setBattleMapTokens(battleMapTokens).setFieldX(FIELD_X).setFieldY(FIELD_Y);
			unitUnderTest.addListener(listener0);
			unitUnderTest.addListener(listener1);
			doThrow(exception).when(listener0).hitsDetected(passed);
			ReflectionTestUtils.setField(unitUnderTest, "log", log);
			// Run
			unitUnderTest.fireEvent(FIELD_X, FIELD_Y, battleMapTokens);
			// Check
			verify(log, times(1)).error(anyString(), eq(exception));
		}

		@Test
		void listenersAreCalledCorrectly_inOrderOfAddition() {
			// Prepare
			HitsGO passed = new HitsGO().setBattleMapTokens(battleMapTokens).setFieldX(FIELD_X).setFieldY(FIELD_Y);
			unitUnderTest.addListener(listener1);
			unitUnderTest.addListener(listener0);
			// Run
			unitUnderTest.fireEvent(FIELD_X, FIELD_Y, battleMapTokens);
			// Check
			InOrder inOrder = inOrder(listener1, listener0);
			inOrder.verify(listener1, times(1)).hitsDetected(passed);
			inOrder.verify(listener0, times(1)).hitsDetected(passed);
		}

		@Test
		void doesNotThrowAnException_addingANullValueAsListener() {
			// Prepare
			unitUnderTest.addListener(null);
			// Run & Check
			assertDoesNotThrow(() -> unitUnderTest.fireEvent(FIELD_X, FIELD_Y, battleMapTokens));
		}
	}

	@Nested
	class mouseClicked_MouseEvent {

		@Test
		void firesAnEventCorrectly() {
			// Prepare
			HitsGO expected = new HitsGO().setBattleMapTokens(battleMapTokens).setFieldX(FIELD_X).setFieldY(FIELD_Y);
			MouseEvent e = mock(MouseEvent.class);
			when(battleMap.getFieldCoordinates(X, Y)).thenReturn(coordinates);
			when(battleMap.getName()).thenReturn(NAME);
			when(battleMapTokenGOMapper.toGOs(List.of(battleMapTokenDTO))).thenReturn(battleMapTokens);
			when(coordinates.getFieldX()).thenReturn(FIELD_X_BD);
			when(coordinates.getFieldY()).thenReturn(FIELD_Y_BD);
			when(e.getX()).thenReturn(X);
			when(e.getY()).thenReturn(Y);
			when(tokenClient.findAllByBattleMap(NAME)).thenReturn(List.of(battleMapTokenDTO));
			when(tokenGUIService.reduceToHitTokens(battleMapTokens, X, Y)).thenReturn(battleMapTokens);
			unitUnderTest.addListener(listener0);
			// Run
			unitUnderTest.mouseClicked(e);
			// Check
			verify(listener0, times(1)).hitsDetected(expected);
		}
	}

	@Nested
	class removeListener_Listener {

		@Test
		void listenerIsNotCalledAnyLonger_whenRemoved() {
			// Prepare
			unitUnderTest.addListener(listener0);
			unitUnderTest.removeListener(listener0);
			// Run
			unitUnderTest.fireEvent(FIELD_X, FIELD_Y, battleMapTokens);
			// Check
			verifyNoInteractions(listener0);
		}

		@Test
		void doesNotThrowAnyException_passingANullValue() {
			assertDoesNotThrow(() -> unitUnderTest.removeListener(null));
		}
	}

	@Nested
	class update {

		@Test
		void happyRun() {
			// Prepare
			int height = 42;
			int width = 1701;
			BufferedImage bufferedImage0 = mock(BufferedImage.class);
			BufferedImage bufferedImage1 = mock(BufferedImage.class);
			Graphics2D graphics = mock(Graphics2D.class);
			ImageIcon imageIcon = mock(ImageIcon.class);
			when(battleMap.getName()).thenReturn(NAME);
			when(bufferedImage1.getGraphics()).thenReturn(graphics);
			when(imageIcon.getIconHeight()).thenReturn(height);
			when(imageIcon.getIconWidth()).thenReturn(width);
			when(battleMap.getImage()).thenReturn(bufferedImage0);
			when(imageIconFactory.create(bufferedImage0)).thenReturn(imageIcon);
			when(imageIconFactory.create(width, height, BufferedImage.TYPE_INT_RGB)).thenReturn(bufferedImage1);
			when(tokenClient.findAllByBattleMap(NAME)).thenReturn(List.of(battleMapTokenDTO));
			// Run & Check
			assertDoesNotThrow(() -> unitUnderTest.update());
		}
	}
}
