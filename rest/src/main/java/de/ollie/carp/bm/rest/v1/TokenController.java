package de.ollie.carp.bm.rest.v1;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.SpielrundeService;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.rest.security.SecurityChecker;
import de.ollie.carp.bm.rest.v1.dto.TokenRequestDTO;
import de.ollie.carp.bm.rest.v1.dto.TokenResponseDTO;
import de.ollie.carp.bm.rest.v1.mapper.TokenDTOMapper;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TokenController.URL)
@RequiredArgsConstructor
public class TokenController {

	static final String URL = RestBase.URL + "/token";

	private final SpielrundeService spielrundeService;
	private final TokenDTOMapper tokenDTOMapper;
	private final TokenService tokenService;
	private final SecurityChecker securityChecker;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenResponseDTO> createTokenWithName(@RequestBody TokenRequestDTO tokenIncomming) {
		securityChecker.throwExceptionIfAccessTokenInvalid(tokenIncomming.getAccessToken());
		Token token = tokenService.createTokenWithName(tokenIncomming.getName());
		return ResponseEntity.ok(tokenDTOMapper.toResponseDTO(token));
	}

	@PostMapping(
		value = "/{tokenId}/{spielrundeId}/{x}/{y}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<HttpStatus> setTokenToBattleMapOfSpielrunde(
		@PathVariable UUID tokenId,
		@PathVariable UUID spielrundeId,
		@PathVariable int x,
		@PathVariable int y
	) {
		System.out.println("\n\nGOTCHA!!!\n\n");
		Spielrunde spielrunde = spielrundeService.findById(spielrundeId).orElseThrow(NoSuchElementException::new);
		Token token = tokenService.findById(tokenId).orElseThrow(NoSuchElementException::new);
		tokenService.addTokenToMapOfSitzung(spielrunde, token, new Coordinates().setX(x).setY(y));
		return ResponseEntity.of(Optional.of(HttpStatus.OK));
	}
}
