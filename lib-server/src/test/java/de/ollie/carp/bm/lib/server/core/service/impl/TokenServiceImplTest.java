package de.ollie.carp.bm.lib.server.core.service.impl;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import de.ollie.carp.bm.lib.server.core.model.SeitenParameter;
import de.ollie.carp.bm.lib.server.core.service.impl.TokenServiceImpl;
import de.ollie.carp.bm.lib.server.core.service.port.TokenPersistencePort;
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
	class findAllTokens_SeitenParameter {

		@Test
		void throwsAnException_passingANullValueAsSeitenParameters() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findAllTokens(null));
		}

		@Test
		void callsThePersistencePortMethodCorrectly() {
			// Prepare
			// Run
			unitUnderTest.findAllTokens(seitenParameter);
			// Check
			verify(persistencePort, times(1)).findAllTokens(seitenParameter);
		}
	}
}
