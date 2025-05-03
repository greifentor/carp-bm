package de.ollie.carp.maps.rest.api.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.ollie.carp.maps.rest.api.core.model.SeitenParameter;
import de.ollie.carp.maps.rest.api.core.service.port.TokenPersistencePort;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenServiceImplTest {

	@Mock
	private SeitenParameter seitenParameter;

	@Mock
	private TokenPersistencePort persistencePort;

	@InjectMocks
	private TokenServiceImpl unitUnderTest;

	@Nested
	class findBy_SeitenParameter {

		@Test
		void throwsAnException_passingANullValueAsSeitenParameters() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findBy(null));
		}

		@Test
		void callsThePersistencePortMethodCorrectly() {
			// Prepare
			// Run
			unitUnderTest.findBy(seitenParameter);
			// Check
			verify(persistencePort, times(1)).findAll(seitenParameter);
		}
	}
}
