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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CarpMapsRestApiStarter.class)
@AutoConfigureMockMvc
class TokenControllerITest {

	@Autowired
	private MockMvc mvc;

	@Test
	void t() throws Exception {
		mvc
			.perform(
				get("/api/v1/tokens?page=1")
					.header(HttpHeaders.AUTHORIZATION, "Bearer ;op")
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk());
	}
}
