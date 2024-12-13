package de.ollie.carp.bm.client.rest;

import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.client.rest.v1.dto.TokenRequestDTO;
import de.ollie.carp.bm.client.rest.v1.dto.TokenResponseDTO;
import de.ollie.carp.bm.client.rest.v1.mapper.TokenDTOMapper;
import de.ollie.carp.bm.core.model.Token;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

@Named
@RequiredArgsConstructor
public class TokenRESTClientImpl implements TokenClient {

	private final TokenDTOMapper tokenMapper;

	private RestClient restClient = RestClient.create();

	@Override
	public Token createTokenWithName(String name) {
		ResponseEntity<TokenResponseDTO> response = restClient
			.post()
			.uri("http://localhost:8080/api/v1/token")
			.contentType(MediaType.APPLICATION_JSON)
			.body(new TokenRequestDTO().setName(name))
			.retrieve()
			.toEntity(TokenResponseDTO.class);
		return tokenMapper.toModel(response.getBody());
	}
}
