package de.ollie.carp.bm.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.service.BattleMapService;
import de.ollie.carp.bm.rest.security.SecurityChecker;
import de.ollie.carp.bm.rest.v1.dto.BattleMapDTO;
import de.ollie.carp.bm.rest.v1.mapper.BattleMapDTOMapper;
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
	class TestsOfMethod_createToken_String {

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
	class TestsOfMethod_findAllTokens {

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

	@Nested
	class TestsOfMethod_setTokenToMapOfSitzung_UUID_UUID_int_int {}
}