package de.ollie.carp.bm.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.rest.security.SecurityChecker;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import de.ollie.carp.bm.rest.v1.mapper.TokenDTOMapper;
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
public class TokenControllerTest {

	private static final String ACCESS_TOKEN = "access-token";
	private static final UUID ID = UUID.randomUUID();
	private static final String NAME = "name";

	@Mock
	private SecurityChecker securityChecker;

	@Mock
	private TokenDTOMapper tokenDTOMapper;

	@Mock
	private TokenDTO tokenRequestDTO;

	@Mock
	private TokenDTO tokenResponseDTO;

	@Mock
	private TokenService tokenService;

	@InjectMocks
	private TokenController unitUnderTest;

	@Nested
	class TestsOfMethod_createToken_String {

		@Test
		void happyRun() {
			// Prepare
			Token token = mock(Token.class);
			ResponseEntity<TokenDTO> expected = ResponseEntity.ok(tokenResponseDTO);
			when(tokenRequestDTO.getName()).thenReturn(NAME);
			when(tokenService.createTokenWithName(NAME)).thenReturn(token);
			when(tokenDTOMapper.toDTO(token)).thenReturn(tokenResponseDTO);
			// Run
			ResponseEntity<TokenDTO> returned = unitUnderTest.createTokenWithName(ACCESS_TOKEN, tokenRequestDTO);
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
			List<TokenDTO> responseList = List.of(tokenResponseDTO);
			ResponseEntity<List<TokenDTO>> expected = ResponseEntity.ok(responseList);
			when(tokenService.findAll()).thenReturn(List.of(token));
			when(tokenDTOMapper.toDTOList(List.of(token))).thenReturn(responseList);
			// Run
			ResponseEntity<List<TokenDTO>> returned = unitUnderTest.findAllTokens(ACCESS_TOKEN);
			// Check
			assertEquals(expected, returned);
		}
	}

	@Nested
	class TestsOfMethod_setTokenToMapOfSitzung_UUID_UUID_int_int {}
}
