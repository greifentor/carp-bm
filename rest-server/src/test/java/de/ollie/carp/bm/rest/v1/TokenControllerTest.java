package de.ollie.carp.bm.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDataDTO;
import de.ollie.carp.bm.client.v1.dto.CoordinatesDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.BattleMapTokenData;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.BattleMapService;
import de.ollie.carp.bm.core.service.BattleMapTokenService;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.rest.v1.mapper.BattleMapTokenDTOMapper;
import de.ollie.carp.bm.rest.v1.mapper.BattleMapTokenDataDTOMapper;
import de.ollie.carp.bm.rest.v1.mapper.CoordinatesDTOMapper;
import de.ollie.carp.bm.rest.v1.mapper.TokenDTOMapper;
import de.ollie.carp.bm.server.security.SecurityChecker;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Disabled;
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
	private static final UUID BATTLE_MAP_TOKEN_ID = UUID.randomUUID();
	private static final BigDecimal FIELD_X = BigDecimal.valueOf(42);
	private static final BigDecimal FIELD_Y = BigDecimal.valueOf(1701);
	private static final UUID TOKEN_ID = UUID.randomUUID();
	private static final String NAME = "name";

	@Mock
	private BattleMap battleMap;

	@Mock
	private BattleMapToken battleMapToken;

	@Mock
	private BattleMapTokenData battleMapTokenData;

	@Mock
	private BattleMapTokenDataDTO battleMapTokenDataDTO;

	@Mock
	private BattleMapTokenDTO battleMapTokenDTO;

	@Mock
	private BattleMapTokenDataDTOMapper battleMapTokenDataDTOMapper;

	@Mock
	private BattleMapTokenDTOMapper battleMapTokenDTOMapper;

	@Mock
	private BattleMapService battleMapService;

	@Mock
	private BattleMapTokenService battleMapTokenService;

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
	class storeToken_String {

		@Disabled
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
			ResponseEntity<TokenDTO> returned = unitUnderTest.storeToken(ACCESS_TOKEN, requestDTO);
			// Check
			assertEquals(expected, returned);
		}
	}

	@Nested
	class delete_String_String {

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
	class findAllTokens {

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
	class findAllByBattleMap_String_String {

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
			when(battleMapService.findByIdOrName(NAME)).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(NoSuchRecordException.class, () -> unitUnderTest.findAllTokenByBattleMap(ACCESS_TOKEN, NAME));
		}
	}

	@Nested
	class findById_String_String {

		@Test
		void callsTheSecurityCheckerCorrectly() {
			// Prepare
			when(service.findByIdOrName(TOKEN_ID.toString())).thenReturn(Optional.of(token));
			// Run
			unitUnderTest.findById(ACCESS_TOKEN, TOKEN_ID.toString());
			// Check
			verify(securityChecker, times(1)).throwExceptionIfAccessTokenInvalid(ACCESS_TOKEN);
		}

		@Test
		void throwsAnException_passingAnIdWhichMatchesNoToken() {
			// Prepare
			when(service.findByIdOrName(TOKEN_ID.toString())).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(NoSuchRecordException.class, () -> unitUnderTest.findById(ACCESS_TOKEN, TOKEN_ID.toString()));
		}

		@Test
		void returnsADto_passingAMatchingId() {
			// Prepare
			when(service.findByIdOrName(TOKEN_ID.toString())).thenReturn(Optional.of(token));
			when(mapper.toDTO(token)).thenReturn(responseDTO);
			// Run
			ResponseEntity<TokenDTO> returned = unitUnderTest.findById(ACCESS_TOKEN, TOKEN_ID.toString());
			// Check
			assertSame(responseDTO, returned.getBody());
		}

		@Test
		void returnsTheOkStatus_passingAMatchingId() {
			// Prepare
			when(service.findByIdOrName(TOKEN_ID.toString())).thenReturn(Optional.of(token));
			when(mapper.toDTO(token)).thenReturn(responseDTO);
			// Run
			ResponseEntity<TokenDTO> returned = unitUnderTest.findById(ACCESS_TOKEN, TOKEN_ID.toString());
			// Check
			assertTrue(returned.getStatusCode().is2xxSuccessful());
		}
	}

	@Nested
	class moveBattleMapToken_String_String_CoordinatesDTO {

		@Test
		void throwsAnException_passingANotExistingBattlemapTokenId() {
			// Prepare
			when(battleMapTokenService.findById(BATTLE_MAP_TOKEN_ID)).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(
				NoSuchRecordException.class,
				() -> unitUnderTest.moveBattleMapToken(ACCESS_TOKEN.toString(), BATTLE_MAP_TOKEN_ID.toString(), coordinatesDTO)
			);
		}

		@Test
		void returnsACorrectResponseEntity_passingAnExistingBattleMapTokenId() {
			// Prepare
			when(battleMapToken.setFieldX(any(BigDecimal.class))).thenReturn(battleMapToken);
			when(battleMapToken.setFieldY(any(BigDecimal.class))).thenReturn(battleMapToken);
			when(battleMapTokenService.findById(BATTLE_MAP_TOKEN_ID)).thenReturn(Optional.of(battleMapToken));
			when(coordinatesDTO.getFieldX()).thenReturn(FIELD_X);
			when(coordinatesDTO.getFieldY()).thenReturn(FIELD_Y);
			// Run
			ResponseEntity<HttpStatus> returned = unitUnderTest.moveBattleMapToken(
				ACCESS_TOKEN.toString(),
				BATTLE_MAP_TOKEN_ID.toString(),
				coordinatesDTO
			);
			// Check
			assertEquals(ResponseEntity.of(Optional.of(HttpStatus.OK)), returned);
		}

		@Test
		void callsServicesSaveMethod_passingAnExistingBattleMapTokenId() {
			// Prepare
			when(battleMapToken.setFieldX(any(BigDecimal.class))).thenReturn(battleMapToken);
			when(battleMapToken.setFieldY(any(BigDecimal.class))).thenReturn(battleMapToken);
			when(battleMapTokenService.findById(BATTLE_MAP_TOKEN_ID)).thenReturn(Optional.of(battleMapToken));
			when(coordinatesDTO.getFieldX()).thenReturn(FIELD_X);
			when(coordinatesDTO.getFieldY()).thenReturn(FIELD_Y);
			// Run
			unitUnderTest.moveBattleMapToken(ACCESS_TOKEN.toString(), BATTLE_MAP_TOKEN_ID.toString(), coordinatesDTO);
			// Check
			verify(battleMapTokenService, times(1)).save(battleMapToken);
			verifyNoMoreInteractions(battleMapTokenService);
		}

		@Test
		void setsCorrectFieldXOfTheBattleMapToken() {
			// Prepare
			when(battleMapToken.setFieldX(any(BigDecimal.class))).thenReturn(battleMapToken);
			when(battleMapToken.setFieldY(any(BigDecimal.class))).thenReturn(battleMapToken);
			when(battleMapTokenService.findById(BATTLE_MAP_TOKEN_ID)).thenReturn(Optional.of(battleMapToken));
			when(coordinatesDTO.getFieldX()).thenReturn(FIELD_X);
			when(coordinatesDTO.getFieldY()).thenReturn(FIELD_Y);
			// Run
			unitUnderTest.moveBattleMapToken(ACCESS_TOKEN.toString(), BATTLE_MAP_TOKEN_ID.toString(), coordinatesDTO);
			// Check
			verify(battleMapToken, times(1)).setFieldX(FIELD_X);
			verifyNoMoreInteractions(battleMapToken);
		}

		@Test
		void setsCorrectFieldYOfTheBattleMapToken() {
			// Prepare
			when(battleMapToken.setFieldX(any(BigDecimal.class))).thenReturn(battleMapToken);
			when(battleMapToken.setFieldY(any(BigDecimal.class))).thenReturn(battleMapToken);
			when(battleMapTokenService.findById(BATTLE_MAP_TOKEN_ID)).thenReturn(Optional.of(battleMapToken));
			when(coordinatesDTO.getFieldX()).thenReturn(FIELD_X);
			when(coordinatesDTO.getFieldY()).thenReturn(FIELD_Y);
			// Run
			unitUnderTest.moveBattleMapToken(ACCESS_TOKEN, BATTLE_MAP_TOKEN_ID.toString(), coordinatesDTO);
			// Check
			verify(battleMapToken, times(1)).setFieldY(FIELD_Y);
			verifyNoMoreInteractions(battleMapToken);
		}
	}

	@Nested
	class setTokenToBattleMap_String_String_String_CoordinatesDTO {

		@Test
		void throwsAnException_passingAnIdOfAnUnknownBattleMap() {
			// Prepare
			when(battleMapService.findByIdOrName(BATTLE_MAP_ID.toString())).thenReturn(Optional.empty());
			// Run & Check
			assertThrows(
				NoSuchRecordException.class,
				() ->
					unitUnderTest.setTokenToBattleMap(
						ACCESS_TOKEN,
						TOKEN_ID.toString(),
						BATTLE_MAP_ID.toString(),
						battleMapTokenDataDTO
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
					unitUnderTest.setTokenToBattleMap(
						ACCESS_TOKEN,
						TOKEN_ID.toString(),
						BATTLE_MAP_ID.toString(),
						battleMapTokenDataDTO
					)
			);
		}

		@Test
		void callsTheServiceMethodToSetTheTokenOnTheBattleMap() {
			// Prepare
			ResponseEntity<HttpStatus> expected = ResponseEntity.of(Optional.of(HttpStatus.OK));
			when(battleMapService.findByIdOrName(BATTLE_MAP_ID.toString())).thenReturn(Optional.of(battleMap));
			when(service.findByIdOrName(TOKEN_ID.toString())).thenReturn(Optional.of(token));
			when(battleMapTokenDataDTOMapper.toModel(battleMapTokenDataDTO)).thenReturn(battleMapTokenData);
			// Run
			ResponseEntity<HttpStatus> returned = unitUnderTest.setTokenToBattleMap(
				ACCESS_TOKEN,
				TOKEN_ID.toString(),
				BATTLE_MAP_ID.toString(),
				battleMapTokenDataDTO
			);
			// Check
			assertEquals(expected, returned);
		}
	}
}
