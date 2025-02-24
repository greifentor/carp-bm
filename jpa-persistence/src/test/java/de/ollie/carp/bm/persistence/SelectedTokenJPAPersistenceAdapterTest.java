package de.ollie.carp.bm.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.SelectedToken;
import de.ollie.carp.bm.persistence.entity.SelectedTokenDBO;
import de.ollie.carp.bm.persistence.mapper.SelectedTokenDBOMapper;
import de.ollie.carp.bm.persistence.repository.SelectedTokenDBORepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SelectedTokenJPAPersistenceAdapterTest {

	@Mock
	private SelectedToken selectedToken;

	@Mock
	private SelectedTokenDBO selectedTokenDBO;

	@Mock
	private SelectedTokenDBOMapper mapper;

	@Mock
	private SelectedTokenDBORepository repository;

	@InjectMocks
	private SelectedTokenJPAPersistenceAdapter unitUnderTest;

	@Nested
	class delete_SelectedToken {

		@Test
		void throwsAnException_passingANullValueAsSelectedToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.delete(null));
		}

		@Test
		void callsTheRepositoryDeleteMethodCorrectly() {
			// Prepare
			when(mapper.toDBO(selectedToken)).thenReturn(selectedTokenDBO);
			// Run
			unitUnderTest.delete(selectedToken);
			// Check
			verify(repository, times(1)).delete(selectedTokenDBO);
		}
	}

	@Nested
	class save_SelectedToken {

		@Test
		void throwsAnException_passingANullValueAsSelectedToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.save(null));
		}

		@Test
		void callsTheRepositorySaveMethodCorrectly() {
			// Prepare
			SelectedToken expected = mock(SelectedToken.class);
			SelectedTokenDBO returnedDBO = mock(SelectedTokenDBO.class);
			when(mapper.toDBO(selectedToken)).thenReturn(selectedTokenDBO);
			when(mapper.toModel(returnedDBO)).thenReturn(expected);
			when(repository.save(selectedTokenDBO)).thenReturn(returnedDBO);
			// Run
			SelectedToken returned = unitUnderTest.save(selectedToken);
			// Check
			assertEquals(expected, returned);
		}
	}
}
