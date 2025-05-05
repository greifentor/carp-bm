package de.ollie.carp.maps.rest.api.rest.v1;

import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter.Richtung;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter.Sortierung;
import de.ollie.carp.maps.rest.api.core.service.MapsTokenImportService;
import de.ollie.carp.maps.rest.api.core.service.SecurityService;
import de.ollie.carp.maps.rest.api.rest.v1.dto.SeiteDTO;
import de.ollie.carp.maps.rest.api.rest.v1.dto.TokenDTO;
import de.ollie.carp.maps.rest.api.rest.v1.mapper.TokenDTOMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(MapsTokenImportController.URL)
@RequiredArgsConstructor
class MapsTokenImportController {

	static final String URL = "api/v1/tokens";
	static final Sortierung SORTIERUNG = new Sortierung("id", Richtung.AUFSTEIGEND);

	private final SecurityService securityService;
	private final TokenDTOMapper tokenMapper;
	private final MapsTokenImportService mapTokenImportService;

	@GetMapping(value = "", produces = "application/json")
	public SeiteDTO<TokenDTO> getTokens(
		@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,
		@RequestParam(required = true, defaultValue = "0") int page,
		@RequestParam(name = "max", defaultValue = "20") int maxRecordsPerPage
	) {
		securityService.checkAuthorization(authorization);
		tokenMapper
			.toDto(mapTokenImportService.findAllTokens(new SeitenParameter(maxRecordsPerPage, page, SORTIERUNG)))
			.getContent()
			.forEach(t -> System.out.println(t.getId() + " - " + (t.getImage() != null ? "" + t.getImage().length : "null")));
		return tokenMapper.toDto(
			mapTokenImportService.findAllTokens(new SeitenParameter(maxRecordsPerPage, page, SORTIERUNG))
		);
	}
}
