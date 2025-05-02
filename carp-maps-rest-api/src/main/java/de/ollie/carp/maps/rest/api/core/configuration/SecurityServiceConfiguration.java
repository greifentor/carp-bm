package de.ollie.carp.maps.rest.api.core.configuration;

import java.util.Set;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class SecurityServiceConfiguration {

	@Value("${security.service.secret}")
	private String secret;

	@Value("${security.service.valid.apps}")
	private Set<String> validApplications;

	@Value("${security.service.valid.uids}")
	private Set<String> validUserIds;
}
