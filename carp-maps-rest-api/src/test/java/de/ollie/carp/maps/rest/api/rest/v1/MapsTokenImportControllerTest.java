package de.ollie.carp.maps.rest.api.rest.v1;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.core.service.MapsTokenImportService;
import de.ollie.carp.maps.rest.api.core.service.SecurityService;
import de.ollie.carp.maps.rest.api.rest.v1.dto.SeiteDTO;
import de.ollie.carp.maps.rest.api.rest.v1.dto.TokenDTO;
import de.ollie.carp.maps.rest.api.rest.v1.mapper.TokenDTOMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MapsTokenImportControllerTest {

	private static final String BEARER = "Bearer";
	private static final int MAX_RECORDS_PER_PAGE = 42;
	private static final int PAGE = 1701;
	private static final String TOKEN = "token";

	private static final String AUTHORIZATION = BEARER + " " + TOKEN;

	@Mock
	private SecurityService securityService;

	@Mock
	private Seite<Token> seite;

	@Mock
	private SeiteDTO<TokenDTO> seiteDTO;

	@Mock
	private TokenDTOMapper tokenMapper;

	@Mock
	private MapsTokenImportService tokenService;

	@InjectMocks
	private MapsTokenImportController unitUnderTest;

	@Nested
	class getTokens_String_int_int {

		@Test
		void callsTheSecurityService_withThePassedAuthorizationToken() {
			// Prepare
			when(
				tokenService.findAllTokens(
					new SeitenParameter(MAX_RECORDS_PER_PAGE, PAGE, MapsTokenImportController.SORTIERUNG)
				)
			)
				.thenReturn(seite);
			when(tokenMapper.toDto(seite)).thenReturn(seiteDTO);
			// Run
			unitUnderTest.getTokens(AUTHORIZATION, PAGE, MAX_RECORDS_PER_PAGE);
			// Check
			verify(securityService, times(1)).checkAuthorization(AUTHORIZATION);
		}
	}
}
