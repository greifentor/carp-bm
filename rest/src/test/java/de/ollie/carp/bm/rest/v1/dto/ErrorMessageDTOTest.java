package de.ollie.carp.bm.rest.v1.dto;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import de.ollie.carp.bm.client.v1.dto.ErrorMessageDTO;
import java.util.Map;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ErrorMessageDTOTest {

	private static final String KEY0 = "key0";
	private static final String KEY1 = "key1";
	private static final String VALUE0 = "value0";
	private static final String VALUE1 = "value1";

	private static final Map<String, String> MAP = Map.of(KEY0, VALUE0, KEY1, VALUE1);

	@InjectMocks
	private ErrorMessageDTO unitUnderTest;

	@Nested
	class getMessageDataToStringArray {

		@Test
		void returnsAnEmptyArray_whenNoMessageDataAreStored() {
			assertArrayEquals(new String[0], unitUnderTest.getMessageDataToStringArray());
		}

		@Test
		void returnsAnArrayWithTwoElements_whenMessageDataContainsOneKeyValuePairOnly() {
			// Prepare
			unitUnderTest.setMessageData(Map.of(KEY0, VALUE0));
			// Run & Check
			assertArrayEquals(new String[] { KEY0, VALUE0 }, unitUnderTest.getMessageDataToStringArray());
		}

		@Test
		void returnsAnArrayWithFourElements_whenMessageDataContainsTwoKeyValuePairs() {
			// Prepare
			unitUnderTest.setMessageData(MAP);
			// Run & Check
			assertArrayEquals(new String[] { KEY0, VALUE0, KEY1, VALUE1 }, unitUnderTest.getMessageDataToStringArray());
		}
	}
}
