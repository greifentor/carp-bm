package de.ollie.carp.bm.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.BattleMapService;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.rest.security.SecurityChecker;
import de.ollie.carp.bm.rest.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.rest.v1.dto.CoordinatesDTO;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import de.ollie.carp.bm.rest.v1.mapper.BattleMapTokenDTOMapper;
import de.ollie.carp.bm.rest.v1.mapper.CoordinatesDTOMapper;
import de.ollie.carp.bm.rest.v1.mapper.TokenDTOMapper;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class TokenControllerTest {

	private static final String ACCESS_TOKEN = "access-token";
	private static final UUID BATTLE_MAP_ID = UUID.randomUUID();
	private static final UUID TOKEN_ID = UUID.randomUUID();
	private static final String NAME = "name";

	@Mock
	private BattleMap battleMap;

	@Mock
	private BattleMapToken battleMapToken;

	@Mock
	private BattleMapTokenDTO battleMapTokenDTO;

	@Mock
	private BattleMapTokenDTOMapper battleMapTokenDTOMapper;

	@Mock
	private BattleMapService battleMapService;

	@Mock
	private Coordinates coordinates;

	@Mock
	private CoordinatesDTO coordinatesDTO;

	@Mock
	private CoordinatesDTOMapper coordinatesMapper;

	@Mock
	private SecurityChecker securityChecker;

	@Mock
	private TokenDTOMapper mapper;

	@Mock
	private Token token;

	@Mock
	private TokenDTO requestDTO;

	@Mock
	private TokenDTO responseDTO;

	@Mock
	private TokenService service;

	@Mock
	private UUIDFactory uuidFactory;

	@InjectMocks
	private TokenController unitUnderTest;

	@Nested
	class TestsOfMethod_createToken_String {

		@Test
		void happyRun() {
			// Prepare
			Token token0 = mock(Token.class);
			Token token1 = mock(Token.class);
			ResponseEntity<TokenDTO> expected = ResponseEntity.ok(responseDTO);
			when(service.create(token0)).thenReturn(token1);
			when(mapper.toModel(requestDTO)).thenReturn(token0);
			when(mapper.toDTO(token1)).thenReturn(responseDTO);
			// Run
			ResponseEntity<TokenDTO> returned = unitUnderTest.createToken(ACCESS_TOKEN, requestDTO);
			// Check
			assertEquals(expected, returned);
		}
	}

	@Nested
	class TestsOfMethod_delete_String_String {

		@Test
		void happyRun() {
			// Prepare
			Token token = mock(Token.class);
			TokenDTO tokenDTO = mock(TokenDTO.class);
			ResponseEntity<TokenDTO> expected = ResponseEntity.ok(tokenDTO);
			when(service.delete(BATTLE_MAP_ID.toString())).thenReturn(token);
			when(mapper.toDTO(token)).thenReturn(tokenDTO);
			// Run
			ResponseEntity<TokenDTO> returned = unitUnderTest.delete(ACCESS_TOKEN, BATTLE_MAP_ID.toString());
			// Check
			assertEquals(expected, returned);
		}
	}

	@Nested
	class TestsOfMethod_findAllTokens {

		@Test
		void happyRun() {
			// Prepare
			Token token = mock(Token.class);
			List<TokenDTO> responseList = List.of(responseDTO);
			ResponseEntity<List<TokenDTO>> expected = ResponseEntity.ok(responseList);
			when(service.findAll()).thenReturn(List.of(token));
			when(mapper.toDTOList(List.of(token))).thenReturn(responseList);
			// Run
			ResponseEntity<List<TokenDTO>> returned = unitUnderTest.findAllTokens(ACCESS_TOKEN);
			// Check
			assertEquals(expected, returned);
		}
	}

	@Nested
	class TestsOfMethod_findAllByBattleMap_String_String {

		@Test
		void happyRun() {
			// Prepare
			List<BattleMapToken> battleMapTokens = List.of(battleMapToken);
			List<BattleMapTokenDTO> responseList = List.of(battleMapTokenDTO);
			ResponseEntity<List<BattleMapTokenDTO>> expected = ResponseEntity.ok(responseList);
			when(battleMapService.findByIdOrName(NAME)).thenReturn(Optional.of(battleMap));
			when(service.findAllByBattleMap(battleMap)).thenReturn(battleMapTokens);
			when(battleMapTokenDTOMapper.toDTOList(battleMapTokens)).thenReturn(responseList);
			// Run
			ResponseEntity<List<BattleMapTokenDTO>> returned = unitUnderTest.findAllTokenByBattleMap(ACCESS_TOKEN, NAME);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void throwsAnException_whenBattleMapNotFound() {
			// Prepare
			List<BattleMapToken> battleMapTokens = List.of(battleMapToken);
			List<BattleMapTokenDTO> responseList = List.of(battleMapTokenDTO);
			ResponseEntity<List<BattleMapTokenDTO>> expected = ResponseEntity.ok(responseList);
			when(battleMapService.findByIdOrName(NAME)).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(NoSuchRecordException.class, () -> unitUnderTest.findAllTokenByBattleMap(ACCESS_TOKEN, NAME));
		}
	}

	@Nested
	class TestsOfMethod_setTokenToMapOfSitzung_String_String_String_CoordinatesDTO {

		@Test
		void throwsAnException_passingAnIdOfAnUnknownBattleMap() {
			// Prepare
			when(battleMapService.findByIdOrName(BATTLE_MAP_ID.toString())).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(
				NoSuchRecordException.class,
				() ->
					unitUnderTest.setTokenToBattleMapOfSpielrunde(
						ACCESS_TOKEN,
						TOKEN_ID.toString(),
						BATTLE_MAP_ID.toString(),
						coordinatesDTO
					)
			);
		}

		@Test
		void throwsAnException_passingAnIdOfAnUnknownToken() {
			// Prepare
			when(battleMapService.findByIdOrName(BATTLE_MAP_ID.toString())).thenReturn(Optional.of(battleMap));
			when(service.findByIdOrName(TOKEN_ID.toString())).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(
				NoSuchRecordException.class,
				() ->
					unitUnderTest.setTokenToBattleMapOfSpielrunde(
						ACCESS_TOKEN,
						TOKEN_ID.toString(),
						BATTLE_MAP_ID.toString(),
						coordinatesDTO
					)
			);
		}

		@Test
		void callsTheServiceMethodToSetTheTokenOnTheBattleMap() {
			// Prepare
			ResponseEntity<HttpStatus> expected = ResponseEntity.of(Optional.of(HttpStatus.OK));
			when(battleMapService.findByIdOrName(BATTLE_MAP_ID.toString())).thenReturn(Optional.of(battleMap));
			when(service.findByIdOrName(TOKEN_ID.toString())).thenReturn(Optional.of(token));
			when(coordinatesMapper.toModel(coordinatesDTO)).thenReturn(coordinates);
			// Run
			ResponseEntity<HttpStatus> returned = unitUnderTest.setTokenToBattleMapOfSpielrunde(
				ACCESS_TOKEN,
				TOKEN_ID.toString(),
				BATTLE_MAP_ID.toString(),
				coordinatesDTO
			);
			// Check
			assertEquals(expected, returned);
		}
	}
}
