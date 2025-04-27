package de.ollie.carp.maps.rest.api.rest.v1;

import java.util.List;
import java.util.UUID;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(TokenController.URL)
class TokenController {

	static final String URL = "api/v1/tokens";

	@GetMapping(value = "", produces = "application/json")
	public List<TokenDTO> getTokens() {
		return List.of(new TokenDTO().setId(UUID.randomUUID()).setName("a-name"));
	}
}
