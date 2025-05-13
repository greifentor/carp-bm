package de.ollie.carp.bm.shell.command.mapper.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import de.ollie.carp.bm.client.v1.dto.DnDTokenDTO;
import de.ollie.carp.bm.client.v1.dto.DnDTokenSizeDTO;
import de.ollie.carp.bm.client.v1.dto.ShapeTypeDTO;
import de.ollie.carp.maps.client.v1.dto.DndDataDTO;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenDTOMapperImplTest {

	private static final UUID UID = UUID.randomUUID();
	private static final byte[] IMAGE = new byte[] { 1, 2, 3, 4, 127, 42 };
	private static final String NAME = "name";
	private static final int RK = 42;
	private static final ShapeTypeDTO SHAPE_TYPE_CIRCLE = ShapeTypeDTO.CIRCLE;
	private static final int TP_MAXIMUM = 1701;

	@InjectMocks
	private TokenDTOMapperImpl unitUnderTest;

	@Nested
	class toDto_TokenDTO {

		@Test
		void returnsNull_passingANullValue() {
			assertNull(unitUnderTest.toDto(null));
		}

		@Test
		void returnsACorrectToken_passingASimpleToken() {
			// Prepare
			DnDTokenDTO expected = (DnDTokenDTO) new DnDTokenDTO()
				.setRk(RK)
				.setTokenSize(DnDTokenSizeDTO.MITTEL)
				.setTpMaximum(TP_MAXIMUM)
				.setId(UID)
				.setImage(IMAGE)
				.setName(NAME)
				.setShapeType(SHAPE_TYPE_CIRCLE);
			de.ollie.carp.maps.client.v1.dto.TokenDTO passed = new de.ollie.carp.maps.client.v1.dto.TokenDTO()
				.setDndData(new DndDataDTO().setRk(RK).setTpMaximum(TP_MAXIMUM))
				.setId(UID)
				.setImage(IMAGE)
				.setName(NAME);
			// Run & Check
			assertEquals(expected, unitUnderTest.toDto(passed));
		}
	}
}
