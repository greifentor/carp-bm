package de.ollie.carp.maps.client.configuration;

import lombok.Generated;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Generated
@Getter
public class MapsRestClientConfiguration {

	@Value("${carp.maps.secret}")
	private String secret;

	@Value("${carp.maps.tokens.url}")
	private String tokensUrl;
}
