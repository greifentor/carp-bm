package de.ollie.carp.maps.rest.api.rest.v1;

import de.ollie.carp.maps.rest.api.core.service.SecurityService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TokenController.URL)
@RequiredArgsConstructor
class TokenController {

	static final String URL = "api/v1/tokens";

	private final SecurityService securityService;

	@GetMapping(value = "", produces = "application/json")
	public List<TokenDTO> getTokens(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@RequestParam(required = true, defaultValue = "0") int page,
		@RequestParam(name = "max", defaultValue = "20") int maxRecordsPerPage
	) {
		securityService.checkAuthorization(authorization);
		return List.of(new TokenDTO().setId(UUID.randomUUID()).setName("a-name"));
	}
}
