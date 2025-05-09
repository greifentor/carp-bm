package de.ollie.carp.maps.rest.api.rest.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.ollie.carp.maps.rest.api.CarpMapsRestApiStarter;
import de.ollie.carp.maps.rest.api.persistence.entity.ImageDBO;
import de.ollie.carp.maps.rest.api.persistence.entity.ImageTypeDBO;
import de.ollie.carp.maps.rest.api.persistence.entity.SitzungTypDBO;
import de.ollie.carp.maps.rest.api.persistence.repository.ImageDBORepository;
import de.ollie.carp.maps.rest.api.rest.v1.dto.RegelsystemDTO;
import de.ollie.carp.maps.rest.api.rest.v1.dto.SeiteDTO;
import de.ollie.carp.maps.rest.api.rest.v1.dto.TokenDTO;
import java.util.Map;
import java.util.UUID;
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
import org.springframework.test.web.servlet.MvcResult;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = CarpMapsRestApiStarter.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class TokenControllerITest {

	private static final byte[] IMAGE = new byte[] { 1, 2, 3, 4, 5 };
	private static final String NAME = "a-name";
	private static final UUID UID = UUID.randomUUID();

	private static final String VALID_TOKEN =
		"eyJhbGciOiJIUzUxMiIsInR5cCI6IkpXVCJ9.eyJhcHAiOiJjYXJwLWJtIiwidWlkIjoidWlkIiwiaWF0IjoxNTE2MjM5MDIyLCJleHAiOjkxNTE2MjM5MTIyfQ.CI-raSXUHcKxIqs0PnZRXLPop7ipB_oeoT9cndsJq-WN71_5pGX5jMDOrzecIPBJX3AMy26lFtMXmveWoAzJTA";

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ImageDBORepository repository;

	@Test
	void happyRun() throws Exception {
		// Prepare
		TokenDTO expected = new TokenDTO().setId(UID).setImage(IMAGE).setName(NAME).setRegelsystem(RegelsystemDTO.DND);
		repository.save(
			new ImageDBO()
				.setId(1L)
				.setGlobalId(UID.toString())
				.setImage(IMAGE)
				.setImageType(ImageTypeDBO.ICON)
				.setName(NAME)
				.setSitzungTyp(SitzungTypDBO.DND)
		);
		// Run
		MvcResult result = mvc
			.perform(
				get("/api/v1/tokens?page=0")
					.header(HttpHeaders.AUTHORIZATION, "Bearer " + VALID_TOKEN)
					.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(status().isOk())
			.andReturn();
		// Check
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = (Map<String, Object>) objectMapper
			.readValue(result.getResponse().getContentAsByteArray(), SeiteDTO.class)
			.getContent()
			.get(0);
		TokenDTO returned = objectMapper.convertValue(map, TokenDTO.class);
		assertEquals(expected, returned);
	}
}
