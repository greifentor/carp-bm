package de.ollie.carp.bm.rest.v1;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.service.SitzungService;
import de.ollie.carp.bm.core.service.TokenService;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(TokenController.URL)
@RequiredArgsConstructor
public class TokenController {

	static final String URL = RestBase.URL + "/token";

	private final SitzungService sitzungService;
	private final TokenService tokenService;

	@PostMapping("/{tokenId}/{sitzungId}/{x}/{y}")
	ResponseEntity<HttpStatus> setTokenToMapOfSitzung(
		@PathVariable UUID tokenId,
		@PathVariable UUID sitzungId,
		@PathVariable int x,
		@PathVariable int y
	) {
		System.out.println("\n\nGOTCHA!!!\n\n");
		Spielrunde sitzung = sitzungService.findById(sitzungId).orElseThrow(NoSuchElementException::new);
		Token token = tokenService.findById(tokenId).orElseThrow(NoSuchElementException::new);
		tokenService.addTokenToMapOfSitzung(sitzung, token, new Coordinates().setX(x).setY(y));
		return ResponseEntity.of(Optional.of(HttpStatus.OK));
	}
}
