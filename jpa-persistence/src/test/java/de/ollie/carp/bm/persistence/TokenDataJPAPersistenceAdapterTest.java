package de.ollie.carp.bm.persistence;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.persistence.entity.TokenDataDBO;
import de.ollie.carp.bm.persistence.mapper.TokenDataDBOMapper;
import de.ollie.carp.bm.persistence.repository.TokenDataDBORepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TokenDataJPAPersistenceAdapterTest {

	private static final UUID UID = UUID.randomUUID();

	@Mock
	private TokenData model;

	@Mock
	private TokenData modelReturn;

	@Mock
	private TokenDataDBO dbo;

	@Mock
	private TokenDataDBO dboReturn;

	@Mock
	private TokenDataDBOMapper mapper;

	@Mock
	private TokenDataDBORepository repository;

	@InjectMocks
	private TokenDataJPAPersistenceAdapter unitUnderTest;

	@Nested
	class deleteById_UUID {

		@Test
		void callsRepositoryMethodCorrectly() {
			unitUnderTest.deleteById(UID);
			verify(repository, times(1)).deleteById(UID);
		}
	}

	@Nested
	class findAll {

		@Test
		void returnsTokenData_returnedByTheRepositoryMethod() {
			// Prepare
			List<TokenDataDBO> tokenDBOs = List.of(dbo);
			List<TokenData> tokens = List.of(model);
			when(repository.findAll()).thenReturn(tokenDBOs);
			when(mapper.toModels(tokenDBOs)).thenReturn(tokens);
			// Run
			List<TokenData> returned = unitUnderTest.findAll();
			// Check
			assertSame(tokens, returned);
		}
	}

	@Nested
	class findById_UUID {

		@Test
		void returnsTokenData_returnedByTheRepositoryMethod() {
			// Prepare
			when(repository.findById(UID)).thenReturn(Optional.of(dbo));
			when(mapper.toModel(dbo)).thenReturn(model);
			// Run
			Optional<TokenData> returned = unitUnderTest.findById(UID);
			// Check
			assertSame(model, returned.get());
		}
	}

	@Nested
	class save_TokenData {

		@Test
		void throwsAnException_passingANullValueAsBattleMapToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.save(null));
		}

		@Test
		void returnsTheObjectReturnedByTheRepositoryCall() {
			// Prepare
			when(mapper.toDBO(model)).thenReturn(dbo);
			when(repository.save(dbo)).thenReturn(dboReturn);
			when(mapper.toModel(dboReturn)).thenReturn(modelReturn);
			// Run
			TokenData returned = unitUnderTest.save(model);
			// Check
			assertSame(modelReturn, returned);
		}
	}
}
