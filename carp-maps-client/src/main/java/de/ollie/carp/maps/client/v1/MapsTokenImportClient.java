package de.ollie.carp.maps.client.v1;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.ollie.carp.maps.client.configuration.MapsRestClientConfiguration;
import de.ollie.carp.maps.client.v1.dto.ErrorMessageDTO;
import de.ollie.carp.maps.client.v1.dto.SeiteDTO;
import de.ollie.carp.maps.client.v1.dto.TokenDTO;
import jakarta.inject.Named;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestClient;

@Named
@RequiredArgsConstructor
public class MapsTokenImportClient {

	private final MapsRestClientConfiguration clientConfiguration;

	private RestClient restClient = RestClient.create();
	private ObjectMapper objectMapper = new ObjectMapper();

	public SeiteDTO<TokenDTO> findAll(int page, int maxRecordsPerPage) {
		Algorithm algorithm = Algorithm.HMAC512(clientConfiguration.getSecret());
		LocalDateTime ldt = LocalDateTime.now().plusMinutes(2);
		Date exp = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		String authorization = JWT
			.create()
			.withClaim("app", "carp-bm")
			.withClaim("uid", "uid")
			.withClaim("iat", new Date())
			.withClaim("exp", exp)
			.sign(algorithm);
		return restClient
			.get()
			.uri(clientConfiguration.getTokensUrl() + "?page=" + page + "&maxRecordsPerPage=" + maxRecordsPerPage)
			.header(HttpHeaders.AUTHORIZATION, "Bearer " + authorization)
			.retrieve()
			.onStatus(status -> status.value() == 404, (req, resp) -> throwServiceExceptionFromErrorResponse(resp))
			.body(new ParameterizedTypeReference<SeiteDTO<TokenDTO>>() {});
	}

	private void throwServiceExceptionFromErrorResponse(ClientHttpResponse response) throws IOException {
		ErrorMessageDTO errorDTO = objectMapper.readValue(
			new String(response.getBody().readAllBytes()),
			ErrorMessageDTO.class
		);
		throw new RuntimeException(errorDTO.getMessage());
	}
}
