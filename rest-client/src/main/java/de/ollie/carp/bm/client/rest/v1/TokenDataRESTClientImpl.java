package de.ollie.carp.bm.client.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ollie.carp.bm.client.TokenDataClient;
import de.ollie.carp.bm.client.rest.v1.mapper.TokenDTOClientMapper;
import de.ollie.carp.bm.client.rest.v1.mapper.TokenDataDTOClientMapper;
import de.ollie.carp.bm.core.exception.ServiceException;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.rest.v1.RestBase;
import de.ollie.carp.bm.rest.v1.dto.DnDTokenDataDTO;
import de.ollie.carp.bm.rest.v1.dto.ErrorMessageDTO;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import de.ollie.carp.bm.rest.v1.dto.TokenDataDTO;
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
public class TokenDataRESTClientImpl implements TokenDataClient {

	private final RestClientConfiguration clientConfiguration;
	private final TokenDTOClientMapper tokenMapper;
	private final TokenDataDTOClientMapper mapper;

	private RestClient restClient = RestClient.create();
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public TokenData createDnDTokenData(Token token, int maximumTp, int rk) {
		ResponseEntity<TokenDataDTO> response = restClient
			.post()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_DATA_URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.contentType(MediaType.APPLICATION_JSON)
			.body(new DnDTokenDataDTO().setRk(rk).setTpMaximum(maximumTp).setToken(tokenMapper.toDTO(token)))
			.retrieve()
			.onStatus(status -> status.value() == 400, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.toEntity(TokenDataDTO.class);
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
	public List<TokenData> findAllTokenData() {
		List<TokenDataDTO> dtos = restClient
			.get()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_DATA_URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(status -> status.value() == 404, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.body(new ParameterizedTypeReference<List<TokenDataDTO>>() {});
		return mapper.toModels(dtos);
	}

	@Override
	public UUID delete(UUID id) {
		TokenDTO dto = restClient
			.delete()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_DATA_URL + "/" + id.toString())
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(status -> status.value() == 404, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.toEntity(TokenDTO.class)
			.getBody();
		return dto != null ? dto.getId() : null;
	}

	@Override
	public TokenData save(TokenData tokenData) {
		// TODO Auto-generated method stub
		return null;
	}
}
