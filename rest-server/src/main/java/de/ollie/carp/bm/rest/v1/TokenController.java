package de.ollie.carp.bm.rest.v1;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.SpielrundeService;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.rest.security.SecurityChecker;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import de.ollie.carp.bm.rest.v1.mapper.TokenDTOMapper;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(RestBase.TOKEN_URL)
@RequiredArgsConstructor
public class TokenController {

	private final SpielrundeService spielrundeService;
	private final TokenDTOMapper mapper;
	private final TokenService service;
	private final SecurityChecker securityChecker;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TokenDTO>> findAllTokens(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		return ResponseEntity.ok(mapper.toDTOList(service.findAll()));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDTO> createToken(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@RequestBody TokenDTO requestDTO
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		Token token = service.create(mapper.toModel(requestDTO));
		return ResponseEntity.ok(mapper.toDTO(token));
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
		Spielrunde spielrunde = spielrundeService.findById(spielrundeId).orElseThrow(NoSuchElementException::new);
		Token token = service.findById(tokenId).orElseThrow(NoSuchElementException::new);
		service.addTokenToMapOfSitzung(spielrunde, token, new Coordinates().setX(x).setY(y));
		return ResponseEntity.of(Optional.of(HttpStatus.OK));
	}

	@DeleteMapping("/{idOrName}")
	public ResponseEntity<Token> delete(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String idOrName
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		return ResponseEntity.ok(service.delete(idOrName));
	}
}
