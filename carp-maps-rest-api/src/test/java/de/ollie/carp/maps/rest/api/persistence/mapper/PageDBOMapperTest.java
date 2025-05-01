package de.ollie.carp.maps.rest.api.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.carp.maps.rest.api.core.model.Seite;
import java.util.List;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
class PageDBOMapperTest {

	private static final int NUMBER_OF_RECORDS_PER_PAGE = 7;
	private static final long NUMBER_OF_RECORDS_TOTAL = 4711;
	private static final int PAGE_NUMBER = 42;

	private static final String DBO = "dbo";
	private static final Object MODEL = new Object();

	@Mock
	private DBOMapper<Object, String> mapper;

	@Mock
	private Page<String> page;

	@Mock
	private Pageable pageable;

	@InjectMocks
	private PageDBOMapper<Object, String> unitUnderTest;

	@Nested
	class toModel_PageD_P {

		@Test
		void throwsAnException_passingANullValueAsPage() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.toModel(null, mapper));
		}

		@Test
		void throwsAnException_passingANullValueAsMapper() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.toModel(page, null));
		}

		@Test
		void callsTheMapperCorrectly() {
			// Prepare
			Seite<Object> expected = new Seite<>(
				NUMBER_OF_RECORDS_TOTAL,
				NUMBER_OF_RECORDS_PER_PAGE,
				List.of(MODEL),
				PAGE_NUMBER
			);
			when(mapper.toModel(DBO)).thenReturn(MODEL);
			when(page.getContent()).thenReturn(List.of(DBO));
			when(page.getNumber()).thenReturn(PAGE_NUMBER);
			when(page.getPageable()).thenReturn(pageable);
			when(page.getTotalElements()).thenReturn(NUMBER_OF_RECORDS_TOTAL);
			when(pageable.getPageSize()).thenReturn(NUMBER_OF_RECORDS_PER_PAGE);
			// Run
			Seite<Object> returned = unitUnderTest.toModel(page, mapper);
			// Check
			assertEquals(expected, returned);
		}
	}
}
