package de.ollie.carp.maps.rest.api.rest.v1;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import de.ollie.carp.maps.rest.api.CarpMapsRestApiStarter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CarpMapsRestApiStarter.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TokenControllerITest {

	private static final String VALID_TOKEN =
		"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhcHAiOiJjYXJwLWJtIiwidWlkIjoidWlkIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjkxNTE2MjM5MTIyfQ.CI-raSXUHcKxIqs0PnZRXLPop7ipB_oeoT9cndsJq-WN71_5pGX5jMDOrzecIPBJX3AMy26lFtMXmveWoAzJTA";

	@Autowired
	private MockMvc mvc;

	@Test
	void happyRun() throws Exception {
		mvc
			.perform(
				get("/api/v1/tokens?page=1")
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + VALID_TOKEN)
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk());
	}
}
