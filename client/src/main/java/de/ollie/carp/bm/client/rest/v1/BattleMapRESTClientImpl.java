package de.ollie.carp.bm.client.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ollie.carp.bm.client.TokenClient;
import de.ollie.carp.bm.client.rest.v1.mapper.TokenDTOClientMapper;
import de.ollie.carp.bm.core.exception.ServiceException;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.rest.v1.RestBase;
import de.ollie.carp.bm.rest.v1.dto.ErrorMessageDTO;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import jakarta.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

@Named
@RequiredArgsConstructor
public class BattleMapRESTClientImpl implements TokenClient {

	private final RestClientConfiguration clientConfiguration;
	private final TokenDTOClientMapper mapper;

	private RestClient restClient = RestClient.create();
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public Token createTokenWithName(String name) {
		ResponseEntity<TokenDTO> response = restClient
			.post()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.contentType(MediaType.APPLICATION_JSON)
			.body(new TokenDTO().setName(name))
			.retrieve()
			.onStatus(
				status -> status.value() == 400,
				(req, resp) -> {
					throwServiceExceptionFromErrorResponse(resp);
				}
			)
			.toEntity(TokenDTO.class);
		return mapper.toModel(response.getBody());
	}

	private void throwServiceExceptionFromErrorResponse(ClientHttpResponse response) throws IOException {
		ErrorMessageDTO errorDTO = objectMapper.readValue(
			new String(response.getBody().readAllBytes()),
			ErrorMessageDTO.class
		);
		throw new ServiceException(
			errorDTO.getMessage(),
			null,
			errorDTO.getMessageId(),
			errorDTO.getMessageDataToStringArray()
		);
	}

	@Override
	public List<Token> findAllTokens() {
		List<TokenDTO> dtos = restClient
			.get()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(
				status -> status.value() == 404,
				(req, resp) -> {
					throwServiceExceptionFromErrorResponse(resp);
				}
			)
			.body(new ParameterizedTypeReference<List<TokenDTO>>() {});
		return mapper.toModels(dtos);
	}

	@Override
	public UUID delete(String uuidToName) {
		return restClient
			.delete()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL + "/" + uuidToName)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(
				status -> status.value() == 404,
				(req, resp) -> {
					throwServiceExceptionFromErrorResponse(resp);
				}
			)
			.toEntity(TokenDTO.class)
			.getBody()
			.getId();
	}
}
