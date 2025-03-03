package de.ollie.carp.bm.client.rest.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ollie.carp.bm.client.v1.TokenClient;
import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDataDTO;
import de.ollie.carp.bm.client.v1.dto.CoordinatesDTO;
import de.ollie.carp.bm.client.v1.dto.DnDTokenDTO;
import de.ollie.carp.bm.client.v1.dto.DnDTokenSizeDTO;
import de.ollie.carp.bm.client.v1.dto.ErrorMessageDTO;
import de.ollie.carp.bm.client.v1.dto.ShapeTypeDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.core.exception.ServiceException;
import de.ollie.carp.bm.rest.v1.RestBase;
import jakarta.inject.Named;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
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
public class TokenRESTClientImpl implements TokenClient {

	private final RestClientConfiguration clientConfiguration;

	private RestClient restClient = RestClient.create();
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	public TokenDTO createToken(String name, byte[] image) {
		ResponseEntity<TokenDTO> response = restClient
			.post()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.contentType(MediaType.APPLICATION_JSON)
			.body(new TokenDTO().setImage(image).setName(name).setShapeType(ShapeTypeDTO.CIRCLE))
			.retrieve()
			.onStatus(status -> status.value() == 400, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.toEntity(TokenDTO.class);
		return response.getBody();
	}

	@Override
	public TokenDTO createDnDToken(String name, byte[] image, int rk, int tpMaximum, DnDTokenSizeDTO dndTokenSize) {
		ResponseEntity<TokenDTO> response = restClient
			.post()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.contentType(MediaType.APPLICATION_JSON)
			.body(
				new DnDTokenDTO()
					.setRk(rk)
					.setTpMaximum(tpMaximum)
					.setTokenSize(DnDTokenSizeDTO.valueOf(dndTokenSize.name()))
					.setImage(image)
					.setName(name)
					.setShapeType(ShapeTypeDTO.CIRCLE)
			)
			.retrieve()
			.onStatus(status -> status.value() == 400, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.toEntity(TokenDTO.class);
		return response.getBody();
	}

	private void throwServiceExceptionFromErrorResponse(ClientHttpResponse response) throws IOException {
		System.out.println("exception thrown");
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
	public List<TokenDTO> findAllTokens() {
		return restClient
			.get()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(status -> status.value() == 404, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.body(new ParameterizedTypeReference<List<TokenDTO>>() {});
	}

	@Override
	public Optional<BattleMapTokenDTO> findSelectedTokenByBattleMap(String battleMapIdOrName) {
		BattleMapTokenDTO dto = restClient
			.get()
			.uri(
				clientConfiguration.getServerSchemaHostAndPort() +
				RestBase.TOKEN_URL +
				"/battlemaps/" +
				battleMapIdOrName +
				"/selected"
			)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(status -> status.value() == 404, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.toEntity(BattleMapTokenDTO.class)
			.getBody();
		return Optional.ofNullable(dto);
	}

	@Override
	public UUID delete(String idOrName) {
		TokenDTO dto = restClient
			.delete()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL + "/" + idOrName)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(status -> status.value() == 404, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.toEntity(TokenDTO.class)
			.getBody();
		return dto != null ? dto.getId() : null;
	}

	@Override
	public String setTokenToBattleMapOfSpielrunde(
		String tokenIdOrName,
		String battleMapIdOrName,
		BattleMapTokenDataDTO battleMapTokenDataDTO
	) {
		restClient
			.post()
			.uri(
				clientConfiguration.getServerSchemaHostAndPort() +
				RestBase.TOKEN_URL +
				"/" +
				tokenIdOrName +
				"/battlemaps/" +
				battleMapIdOrName
			)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.contentType(MediaType.APPLICATION_JSON)
			.body(battleMapTokenDataDTO)
			.retrieve()
			.onStatus(status -> status.value() >= 400, (req, resp) -> throwServiceExceptionFromErrorResponse(resp));
		return "set token to battlemap.";
	}

	@Override
	public List<BattleMapTokenDTO> findAllByBattleMap(String battleMapIdOrName) {
		return restClient
			.get()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL + "/battlemaps/" + battleMapIdOrName)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(status -> status.value() == 404, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.body(new ParameterizedTypeReference<List<BattleMapTokenDTO>>() {});
	}

	@Override
	public TokenDTO getByIdOrName(String idOrName) {
		return restClient
			.get()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL + "/" + idOrName)
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.retrieve()
			.onStatus(status -> status.value() == 404, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.toEntity(TokenDTO.class)
			.getBody();
	}

	@Override
	public String moveBattleMapToken(String battleMapTokenId, CoordinatesDTO coordinates) {
		restClient
			.post()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL + "/" + battleMapTokenId + "/move")
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.contentType(MediaType.APPLICATION_JSON)
			.body(coordinates)
			.retrieve()
			.onStatus(status -> status.value() >= 400, (req, resp) -> throwServiceExceptionFromErrorResponse(resp));
		return "moved token to " + coordinates.getFieldX() + "/" + coordinates.getFieldY() + ".";
	}

	@Override
	public void selectToken(String battleMapTokenId) {
		restClient
			.post()
			.uri(clientConfiguration.getServerSchemaHostAndPort() + RestBase.TOKEN_URL + "/" + battleMapTokenId + "/select")
			.header(HttpHeaders.AUTHORIZATION, ";op")
			.contentType(MediaType.APPLICATION_JSON)
			.retrieve()
			.onStatus(status -> status.value() >= 400, (req, resp) -> throwServiceExceptionFromErrorResponse(resp));
	}
}
