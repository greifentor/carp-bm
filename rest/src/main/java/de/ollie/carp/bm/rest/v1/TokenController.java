package de.ollie.carp.bm.rest.v1;

import java.util.UUID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TokenController.URL)
public class TokenController {

	static final String URL = RestBase.URL + "/token";

	@PostMapping("/{tokenId}/{sitzungId}")
	void setTokenToMapOfSitzung(@PathVariable UUID tokenId, @PathVariable UUID sitzungId) {}
}
