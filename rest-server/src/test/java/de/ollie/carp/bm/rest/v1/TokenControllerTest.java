package de.ollie.carp.bm.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.rest.security.SecurityChecker;
import de.ollie.carp.bm.rest.v1.dto.CoordinatesDTO;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
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
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class TokenControllerTest {

	private static final String ACCESS_TOKEN = "access-token";
	private static final UUID BATTLE_MAP_ID = UUID.randomUUID();
	private static final UUID TOKEN_ID = UUID.randomUUID();
	private static final String NAME = "name";

	@Mock
	private CoordinatesDTO coordinatesDTO;

	@Mock
	private SecurityChecker securityChecker;

	@Mock
	private TokenDTOMapper mapper;

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
	class TestsOfMethod_setTokenToMapOfSitzung_String_String_String_CoordinatesDTO {

		@Test
		void throwsAnException_passingAnIdOfAnUnknownBattleMap() {
			// Prepare
			when(uuidFactory.createFromString(TOKEN_ID.toString())).thenReturn(TOKEN_ID);
			when(service.findById(TOKEN_ID)).thenReturn(Optional.empty());
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
	}
}
