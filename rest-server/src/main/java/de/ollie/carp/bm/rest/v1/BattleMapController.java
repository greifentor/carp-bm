package de.ollie.carp.bm.rest.v1;

import de.ollie.carp.bm.client.v1.dto.BattleMapDTO;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.service.BattleMapService;
import de.ollie.carp.bm.rest.v1.mapper.BattleMapDTOMapper;
import de.ollie.carp.bm.server.security.SecurityChecker;
import java.util.List;
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
@RequestMapping(RestBase.BATTLE_MAP_URL)
@RequiredArgsConstructor
public class BattleMapController {

	private final BattleMapDTOMapper mapper;
	private final BattleMapService service;
	private final SecurityChecker securityChecker;

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BattleMapDTO>> findAllBattleMaps(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		return ResponseEntity.ok(mapper.toDTOList(service.findAll()));
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<BattleMapDTO> createBattleMap(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@RequestBody BattleMapDTO requestDTO
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		BattleMap battleMap = service.create(mapper.toModel(requestDTO));
		return ResponseEntity.ok(mapper.toDTO(battleMap));
	}

	@DeleteMapping("/{idOrName}")
	public ResponseEntity<BattleMapDTO> delete(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String accessToken,
		@PathVariable String idOrName
	) {
		securityChecker.throwExceptionIfAccessTokenInvalid(accessToken);
		return ResponseEntity.ok(mapper.toDTO(service.delete(idOrName)));
	}
}
