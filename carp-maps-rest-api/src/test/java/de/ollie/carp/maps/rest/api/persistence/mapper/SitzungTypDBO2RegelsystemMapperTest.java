package de.ollie.carp.maps.rest.api.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import de.ollie.carp.maps.rest.api.core.model.Regelsystem;
import de.ollie.carp.maps.rest.api.persistence.entity.SitzungTypDBO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SitzungTypDBO2RegelsystemMapperTest {

	@InjectMocks
	private SitzungTypDBO2RegelsystemMapper unitUnderTest;

	@Nested
	class map_SitzungTypDBO {

		@Test
		void returnsNull_passingANullValue() {
			assertNull(unitUnderTest.map(null));
		}

		@ParameterizedTest
		@EnumSource(SitzungTypDBO.class)
		void returnsAValueForEachIdentifier(SitzungTypDBO dbo) {
			assertNotNull(unitUnderTest.map(dbo));
		}

		@Test
		void returnsTheCorrectResult_passingIndentifierDnD() {
			assertEquals(Regelsystem.DND, unitUnderTest.map(SitzungTypDBO.DND));
		}

		@Test
		void returnsTheCorrectResult_passingIndentifierSpace1889() {
			assertEquals(Regelsystem.SPACE_1889, unitUnderTest.map(SitzungTypDBO.SPACE_1889));
		}

		@Test
		void returnsTheCorrectResult_passingIndentifierStarWars() {
			assertEquals(Regelsystem.STAR_WARS, unitUnderTest.map(SitzungTypDBO.STAR_WARS));
		}
	}
}
