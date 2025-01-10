package de.ollie.carp.bm.client.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
public class RestClientConfigurationTest {

	private static final Integer PORT = 42;

	@InjectMocks
	private RestClientConfiguration unitUnderTest;

	@Nested
	class getServerHostAndPort {

		@Test
		void returnsCorrectString_whenSchemaAndHostSetOnly() {
			// Prepare
			ReflectionTestUtils.setField(unitUnderTest, "host", RestClientConfiguration.DEFAULT_HOST);
			ReflectionTestUtils.setField(unitUnderTest, "schema", RestClientConfiguration.DEFAULT_SCHEMA);
			// Run & Check
			assertEquals(
				RestClientConfiguration.DEFAULT_SCHEMA + "://" + RestClientConfiguration.DEFAULT_HOST,
				unitUnderTest.getServerSchemaHostAndPort()
			);
		}

		@Test
		void returnsCorrectString_whenSchemaHostAndPortSet() {
			// Prepare
			ReflectionTestUtils.setField(unitUnderTest, "host", RestClientConfiguration.DEFAULT_HOST);
			ReflectionTestUtils.setField(unitUnderTest, "port", PORT);
			ReflectionTestUtils.setField(unitUnderTest, "schema", RestClientConfiguration.DEFAULT_SCHEMA);
			// Run & Check
			assertEquals(
				RestClientConfiguration.DEFAULT_SCHEMA + "://" + RestClientConfiguration.DEFAULT_HOST + ":" + PORT,
				unitUnderTest.getServerSchemaHostAndPort()
			);
		}
	}
}
