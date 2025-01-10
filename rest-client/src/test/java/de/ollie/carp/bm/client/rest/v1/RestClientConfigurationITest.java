package de.ollie.carp.bm.client.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import jakarta.inject.Inject;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = RestClientConfiguration.class)
@ActiveProfiles("defaults-only")
public class RestClientConfigurationITest {

	@Inject
	private RestClientConfiguration unitUnderTest;

	@Nested
	class getServerHostAndPort {

		@Test
		void returnsCorrectString_withDefaultValues() {
			assertEquals(
				RestClientConfiguration.DEFAULT_SCHEMA +
				"://" +
				RestClientConfiguration.DEFAULT_HOST +
				":" +
				RestClientConfiguration.DEFAULT_PORT,
				unitUnderTest.getServerSchemaHostAndPort()
			);
		}
	}
}
