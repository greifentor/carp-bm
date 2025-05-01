package de.ollie.carp.maps.rest.api.persistence.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter.Richtung;
import de.ollie.carp.maps.rest.api.core.model.SeitenParameter.Sortierung;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

@ExtendWith(MockitoExtension.class)
public class PageableMapperTest {

	private static final int PAGE_NUMBER = 42;
	private static final int PAGE_SIZE = 7;
	private static final String PROPERTY = "property";
	private static final Sort SORT = Sort.by(Order.by(PROPERTY));

	@InjectMocks
	private PageableMapper unitUnderTest;

	@Nested
	class toDbo_SeitenParameter {

		@Test
		void throwsAnException_passingANullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.toDbo(null));
		}

		@Test
		void returnsACorrectPageRequest_passingAFullyLoadedSeitenParameter() {
			// Prepare
			PageRequest expected = PageRequest.of(PAGE_NUMBER, PAGE_SIZE, SORT);
			SeitenParameter seitenParameter = new SeitenParameter(PAGE_SIZE, PAGE_NUMBER, new Sortierung(PROPERTY));
			// Run
			PageRequest returned = unitUnderTest.toDbo(seitenParameter);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsACorrectPageRequest_passingSeitenParameterWithNoSorting() {
			// Prepare
			PageRequest expected = PageRequest.of(PAGE_NUMBER, PAGE_SIZE, Sort.unsorted());
			SeitenParameter seitenParameter = new SeitenParameter(PAGE_SIZE, PAGE_NUMBER);
			// Run
			PageRequest returned = unitUnderTest.toDbo(seitenParameter);
			// Check
			assertEquals(expected, returned);
		}

		@Test
		void returnsACorrectPageRequest_passingSeitenParameterWithDescSorting() {
			// Prepare
			PageRequest expected = PageRequest.of(PAGE_NUMBER, PAGE_SIZE, Sort.by(Order.desc(PROPERTY)));
			SeitenParameter seitenParameter = new SeitenParameter(
				PAGE_SIZE,
				PAGE_NUMBER,
				new Sortierung(PROPERTY, Richtung.ABSTEIGEND)
			);
			// Run
			PageRequest returned = unitUnderTest.toDbo(seitenParameter);
			// Check
			assertEquals(expected, returned);
		}
	}
}
