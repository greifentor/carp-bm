package de.ollie.carp.bm.persistence;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.exception.UniqueConstraintViolationException;
import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.persistence.entity.BattleMapDBO;
import de.ollie.carp.bm.persistence.mapper.BattleMapDBOMapper;
import de.ollie.carp.bm.persistence.repository.BattleMapDBORepository;
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
public class BattleMapJPAPersistenceAdapterTest {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private BattleMap model0;

	@Mock
	private BattleMap model1;

	@Mock
	private BattleMapDBO dbo;

	@Mock
	private BattleMapDBOMapper mapper;

	@Mock
	private BattleMapDBORepository repository;

	@InjectMocks
	private BattleMapJPAPersistenceAdapter unitUnderTest;

	@Nested
	class create_Token {

		@Test
		void throwsAnException_passingAnAlreadyExistingName() {
			// Prepare
			when(model0.getName()).thenReturn(NAME);
			when(repository.findByName(NAME)).thenReturn(Optional.of(dbo));
			// Run & Check
			assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest.create(model0));
			verifyNoMoreInteractions(mapper, repository);
		}

		@Test
		void returnsANewTokenWithPassedNameAndSetUUID() {
			// Prepare
			when(model0.getName()).thenReturn(NAME);
			when(repository.findByName(NAME)).thenReturn(Optional.empty());
			when(repository.save(dbo)).thenReturn(dbo);
			when(mapper.toDBO(model0)).thenReturn(dbo);
			when(mapper.toModel(dbo)).thenReturn(model1);
			// Run
			BattleMap returned = unitUnderTest.create(model0);
			// Check
			assertSame(model1, returned);
		}
	}

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
			List<BattleMapDBO> tokenDBOs = List.of(dbo);
			List<BattleMap> tokens = List.of(model0);
			when(repository.findAll()).thenReturn(tokenDBOs);
			when(mapper.toModels(tokenDBOs)).thenReturn(tokens);
			// Run
			List<BattleMap> returned = unitUnderTest.findAll();
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
			when(mapper.toModel(dbo)).thenReturn(model0);
			// Run
			Optional<BattleMap> returned = unitUnderTest.findById(UID);
			// Check
			assertSame(model0, returned.get());
		}
	}

	@Nested
	class findByName_String {

		@Test
		void returnsTokenData_returnedByTheRepositoryMethod() {
			// Prepare
			when(repository.findByName(NAME)).thenReturn(Optional.of(dbo));
			when(mapper.toModel(dbo)).thenReturn(model0);
			// Run
			Optional<BattleMap> returned = unitUnderTest.findByName(NAME);
			// Check
			assertSame(model0, returned.get());
		}
	}
}
