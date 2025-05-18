package de.ollie.carp.bm.rest.v1;

import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDataDTO;
import de.ollie.carp.bm.client.v1.dto.CoordinatesDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.core.exception.NoSuchRecordException;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.BattleMapService;
import de.ollie.carp.bm.core.service.BattleMapTokenService;
import de.ollie.carp.bm.core.service.SelectedTokenService;
import de.ollie.carp.bm.core.service.TokenService;
import de.ollie.carp.bm.rest.v1.mapper.BattleMapTokenDTOMapper;
import de.ollie.carp.bm.rest.v1.mapper.BattleMapTokenDataDTOMapper;
import de.ollie.carp.bm.rest.v1.mapper.CoordinatesDTOMapper;
import de.ollie.carp.bm.rest.v1.mapper.TokenDTOMapper;
import de.ollie.carp.bm.server.security.SecurityChecker;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

	private static final Logger LOG = LogManager.getLogger(TokenController.class);

	private final BattleMapService battleMapService;
	private final BattleMapTokenService battleMapTokenService;
	private final BattleMapTokenDTOMapper battleMapTokenMapper;
	private final BattleMapTokenDataDTOMapper battleMapTokenDataMapper;
	private final CoordinatesDTOMapper coordinatesMapper;
	private final SecurityChecker securityChecker;
	private final SelectedTokenService selectedTokenService;
	private final TokenDTOMapper mapper;
	private final TokenService tokenService;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TokenDTO>> findAllTokens(@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken) {
		LOG.info("server - findAllTokens");
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		return ResponseEntity.ok(mapper.toDTOList(tokenService.findAll()));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TokenDTO> storeToken(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@RequestBody TokenDTO requestDTO
	) {
		LOG.info("server - storeToken: " + requestDTO);
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		Token token = tokenService.updateOrCreate(mapper.toModel(requestDTO));
		return ResponseEntity.ok(mapper.toDTO(token));
	}

	@GetMapping("/{idOrName}")
	public ResponseEntity<TokenDTO> findById(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String idOrName
	) {
		LOG.info("server - findById: " + idOrName);
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		return ResponseEntity.ok(
			mapper.toDTO(
				tokenService.findByIdOrName(idOrName).orElseThrow(() -> new NoSuchRecordException(idOrName, "Token", "id"))
			)
		);
	}

	@GetMapping(value = "/battlemaps/{battleMapIdOrName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BattleMapTokenDTO>> findAllTokenByBattleMap(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String battleMapIdOrName
	) {
		LOG.info("server - findAllTokenByBattleMap: " + battleMapIdOrName);
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		BattleMap battleMap = battleMapService
			.findByIdOrName(battleMapIdOrName)
			.orElseThrow(() -> new NoSuchRecordException(battleMapIdOrName, "BattleMap", "id"));
		return ResponseEntity.ok(battleMapTokenMapper.toDTOList(tokenService.findAllByBattleMap(battleMap)));
	}

	@GetMapping(value = "/battlemaps/{battleMapIdOrName}/selected", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BattleMapTokenDTO> findSelectedTokenByBattleMap(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String battleMapIdOrName
	) {
		LOG.info("server - findSelectedTokenByBattleMap: " + battleMapIdOrName);
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		BattleMap battleMap = battleMapService
			.findByIdOrName(battleMapIdOrName)
			.orElseThrow(() -> new NoSuchRecordException(battleMapIdOrName, "BattleMap", "id"));
		return ResponseEntity.ok(
			tokenService.findSelectedTokenByBattleMap(battleMap).map(battleMapTokenMapper::toDTO).orElse(null)
		);
	}

	@PostMapping(
		value = "/{battleMapTokenId}/move",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<HttpStatus> moveBattleMapToken(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String battleMapTokenId,
		@RequestBody CoordinatesDTO coordinatesDTO
	) {
		LOG.info("server - moveBattleMapToken: " + battleMapTokenId);
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		BattleMapToken battleMapToken = battleMapTokenService
			.findById(UUID.fromString(battleMapTokenId))
			.orElseThrow(() -> new NoSuchRecordException(battleMapTokenId, "BattleMapToken", "id"));
		battleMapToken.setFieldX(coordinatesDTO.getFieldX()).setFieldY(coordinatesDTO.getFieldY());
		battleMapTokenService.save(battleMapToken);
		return ResponseEntity.of(Optional.of(HttpStatus.OK));
	}

	@PostMapping(
		value = "/selectedtokens/{battleMapTokenId}/select",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<HttpStatus> selectToken(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String battleMapTokenId
	) {
		LOG.info("select token (" + battleMapTokenId + ")");
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		BattleMapToken battleMapToken = battleMapTokenService
			.findById(UUID.fromString(battleMapTokenId))
			.orElseThrow(() -> new NoSuchRecordException(battleMapTokenId, "BattleMapToken", "id"));
		selectedTokenService.selectToken(battleMapToken);
		LOG.info("select token (" + battleMapTokenId + ")");
		return ResponseEntity.of(Optional.of(HttpStatus.OK));
	}

	@DeleteMapping(
		value = "/selectedtokens/{battleMapTokenId}/unselect",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<HttpStatus> unselectToken(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String battleMapTokenId
	) {
		LOG.info("server - token unselect: " + battleMapTokenId);
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		BattleMapToken battleMapToken = battleMapTokenService
			.findById(UUID.fromString(battleMapTokenId))
			.orElseThrow(() -> new NoSuchRecordException(battleMapTokenId, "BattleMapToken", "id"));
		selectedTokenService.unselectToken(battleMapToken);
		LOG.info("server - token unselected: " + battleMapTokenId);
		return ResponseEntity.of(Optional.of(HttpStatus.OK));
	}

	@PostMapping(
		value = "/{tokenIdOrName}/battlemaps/{battleMapIdOrName}",
		consumes = MediaType.APPLICATION_JSON_VALUE,
		produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<HttpStatus> setTokenToBattleMap(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String tokenIdOrName,
		@PathVariable String battleMapIdOrName,
		@RequestBody BattleMapTokenDataDTO battleMapTokenDTO
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		LOG.info("server - setTokenToBattleMap: " + tokenIdOrName);
		BattleMap battleMap = battleMapService
			.findByIdOrName(battleMapIdOrName)
			.orElseThrow(() -> new NoSuchRecordException(battleMapIdOrName, "BattleMap", "id"));
		Token token = tokenService
			.findByIdOrName(tokenIdOrName)
			.orElseThrow(() -> new NoSuchRecordException(tokenIdOrName, "Token", "id"));
		tokenService.addTokenToBattleMap(token, battleMap, battleMapTokenDataMapper.toModel(battleMapTokenDTO));
		return ResponseEntity.of(Optional.of(HttpStatus.OK));
	}

	@DeleteMapping("/{idOrName}")
	public ResponseEntity<TokenDTO> delete(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String idOrName
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		LOG.info("server - delete: " + idOrName);
		return ResponseEntity.ok(mapper.toDTO(tokenService.delete(idOrName)));
	}
}
