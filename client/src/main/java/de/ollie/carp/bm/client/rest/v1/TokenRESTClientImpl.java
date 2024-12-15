package de.ollie.carp.bm.client.rest.v1;

import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.client.rest.v1.mapper.TokenDTOClientMapper;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.rest.v1.TokenController;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import jakarta.inject.Named;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;

@Named
@RequiredArgsConstructor
public class TokenRESTClientImpl implements TokenClient {

	private final RestClientConfiguration clientConfiguration;
	private final TokenDTOClientMapper tokenMapper;

	private RestClient restClient = RestClient.create();

	@Override
	public Token createTokenWithName(String name) {
		ResponseEntity<TokenDTO> response = restClient
			.post()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + TokenController.URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.contentType(MediaType.APPLICATION_JSON)
			.body(new TokenDTO().setName(name))
			.retrieve()
			.toEntity(TokenDTO.class);
		return tokenMapper.toModel(response.getBody());
	}

	@Override
	public List<Token> findAllTokens() {
		List<TokenDTO> dtos = restClient
			.get()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + TokenController.URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.body(new ParameterizedTypeReference<List<TokenDTO>>() {});
		return tokenMapper.toModels(dtos);
	}
}
