package de.ollie.carp.bm.rest.v1;

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

	private final SecurityChecker securityChecker;
	private final TokenDTOMapper mapper;
	private final TokenService service;
	private final UUIDFactory uuidFactory;

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
		value = "/{tokenId}/battlemaps/{battleMapId}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<HttpStatus> setTokenToBattleMapOfSpielrunde(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String tokenId,
		@PathVariable String battleMapId,
		@RequestBody CoordinatesDTO coordinatesDTO
	) {
		Token token = service
			.findById(uuidFactory.createFromString(tokenId))
			.orElseThrow(() -> new NoSuchRecordException(tokenId, "Token", "id"));
		service.addTokenToBattleMap(null, null, null);
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
