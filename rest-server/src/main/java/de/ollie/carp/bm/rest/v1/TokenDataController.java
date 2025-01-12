package de.ollie.carp.bm.rest.v1;

import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.core.service.TokenDataService;
import de.ollie.carp.bm.rest.security.SecurityChecker;
import de.ollie.carp.bm.rest.v1.dto.TokenDataDTO;
import de.ollie.carp.bm.rest.v1.mapper.TokenDataDTOMapper;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
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
@RequestMapping(RestBase.TOKEN_DATA_URL)
@RequiredArgsConstructor
public class TokenDataController {

	private final SecurityChecker securityChecker;
	private final TokenDataDTOMapper mapper;
	private final TokenDataService tokenDataService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TokenDataDTO>> findAllTokenData(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		return ResponseEntity.ok(mapper.toDTOList(tokenDataService.findAll()));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDataDTO> createTokenData(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@RequestBody TokenDataDTO requestDTO
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		TokenData tokenData = tokenDataService.create(mapper.toModel(requestDTO));
		return ResponseEntity.ok(mapper.toDTO(tokenData));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<TokenDataDTO> delete(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable UUID id
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		return ResponseEntity.ok(mapper.toDTO(tokenDataService.deleteById(id)));
	}
}
