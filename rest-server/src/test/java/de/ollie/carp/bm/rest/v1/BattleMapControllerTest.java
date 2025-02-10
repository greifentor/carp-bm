package de.ollie.carp.bm.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.client.v1.dto.BattleMapDTO;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.service.BattleMapService;
import de.ollie.carp.bm.rest.v1.mapper.BattleMapDTOMapper;
import de.ollie.carp.bm.server.security.SecurityChecker;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class BattleMapControllerTest {

	private static final String ACCESS_TOKEN = "access-token";
	private static final UUID ID = UUID.randomUUID();
	private static final String NAME = "name";

	@Mock
	private SecurityChecker securityChecker;

	@Mock
	private BattleMapDTOMapper mapper;

	@Mock
	private BattleMapDTO requestDTO;

	@Mock
	private BattleMapDTO responseDTO;

	@Mock
	private BattleMapService service;

	@InjectMocks
	private BattleMapController unitUnderTest;

	@Nested
	class createToken_String {

		@Test
		void happyRun() {
			// Prepare
			BattleMap token0 = mock(BattleMap.class);
			BattleMap token1 = mock(BattleMap.class);
			ResponseEntity<BattleMapDTO> expected = ResponseEntity.ok(responseDTO);
			when(service.create(token0)).thenReturn(token1);
			when(mapper.toModel(requestDTO)).thenReturn(token0);
			when(mapper.toDTO(token1)).thenReturn(responseDTO);
			// Run
			ResponseEntity<BattleMapDTO> returned = unitUnderTest.createBattleMap(ACCESS_TOKEN, requestDTO);
			// Check
			assertEquals(expected, returned);
		}
	}

	@Nested
	class delete_String_String {

		@Test
		void happyRun() {
			// Prepare
			BattleMap battleMap = mock(BattleMap.class);
			BattleMapDTO battleMapDTO = mock(BattleMapDTO.class);
			ResponseEntity<BattleMapDTO> expected = ResponseEntity.ok(battleMapDTO);
			when(service.delete(ID.toString())).thenReturn(battleMap);
			when(mapper.toDTO(battleMap)).thenReturn(battleMapDTO);
			// Run
			ResponseEntity<BattleMapDTO> returned = unitUnderTest.delete(ACCESS_TOKEN, ID.toString());
			// Check
			assertEquals(expected, returned);
		}
	}

	@Nested
	class findAllTokens {

		@Test
		void happyRun() {
			// Prepare
			BattleMap token = mock(BattleMap.class);
			List<BattleMapDTO> responseList = List.of(responseDTO);
			ResponseEntity<List<BattleMapDTO>> expected = ResponseEntity.ok(responseList);
			when(service.findAll()).thenReturn(List.of(token));
			when(mapper.toDTOList(List.of(token))).thenReturn(responseList);
			// Run
			ResponseEntity<List<BattleMapDTO>> returned = unitUnderTest.findAllBattleMaps(ACCESS_TOKEN);
			// Check
			assertEquals(expected, returned);
		}
	}
}
