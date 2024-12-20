package de.ollie.carp.bm.persistence;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.exception.UniqueConstraintViolationException;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import de.ollie.carp.bm.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.bm.persistence.repository.TokenDBORepository;
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
public class TokenJPAPersistenceAdapterTest {

	private static final String NAME = "name";
	private static final UUID UID = UUID.randomUUID();

	@Mock
	private Token token0;

	@Mock
	private Token token1;

	@Mock
	private TokenDBO tokenDBO;

	@Mock
	private TokenDBOMapper tokenDBOMapper;

	@Mock
	private TokenDBORepository tokenDBORepository;

	@InjectMocks
	private TokenJPAPersistenceAdapter unitUnderTest;

	@Nested
	class TestsOfTheMethod_create_Token {

		@Test
		void throwsAnException_passingAnAlreadyExistingName() {
			// Prepare
			when(token0.getName()).thenReturn(NAME);
			when(tokenDBORepository.findByName(NAME)).thenReturn(Optional.of(tokenDBO));
			// Run & Check
			assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest.create(token0));
			verifyNoMoreInteractions(tokenDBOMapper, tokenDBORepository);
		}

		@Test
		void returnsANewTokenWithPassedNameAndSetUUID() {
			// Prepare
			when(token0.getName()).thenReturn(NAME);
			when(tokenDBORepository.findByName(NAME)).thenReturn(Optional.empty());
			when(tokenDBORepository.save(tokenDBO)).thenReturn(tokenDBO);
			when(tokenDBOMapper.toDBO(token0)).thenReturn(tokenDBO);
			when(tokenDBOMapper.toModel(tokenDBO)).thenReturn(token1);
			// Run
			Token returned = unitUnderTest.create(token0);
			// Check
			assertSame(token1, returned);
		}
	}

	@Nested
	class TestsOfMethod_deleteById_UUID {

		@Test
		void callsRepositoryMethodCorrectly() {
			unitUnderTest.deleteById(UID);
			verify(tokenDBORepository, times(1)).deleteById(UID);
		}
	}

	@Nested
	class TestsOfMethod_findAll {

		@Test
		void returnsTokenData_returnedByTheRepositoryMethod() {
			// Prepare
			List<TokenDBO> tokenDBOs = List.of(tokenDBO);
			List<Token> tokens = List.of(token0);
			when(tokenDBORepository.findAll()).thenReturn(tokenDBOs);
			when(tokenDBOMapper.toModels(tokenDBOs)).thenReturn(tokens);
			// Run
			List<Token> returned = unitUnderTest.findAll();
			// Check
			assertSame(tokens, returned);
		}
	}

	@Nested
	class TestsOfMethod_findById_UUID {

		@Test
		void returnsTokenData_returnedByTheRepositoryMethod() {
			// Prepare
			when(tokenDBORepository.findById(UID)).thenReturn(Optional.of(tokenDBO));
			when(tokenDBOMapper.toModel(tokenDBO)).thenReturn(token0);
			// Run
			Optional<Token> returned = unitUnderTest.findById(UID);
			// Check
			assertSame(token0, returned.get());
		}
	}

	@Nested
	class TestsOfMethod_findByName_String {

		@Test
		void returnsTokenData_returnedByTheRepositoryMethod() {
			// Prepare
			when(tokenDBORepository.findByName(NAME)).thenReturn(Optional.of(tokenDBO));
			when(tokenDBOMapper.toModel(tokenDBO)).thenReturn(token0);
			// Run
			Optional<Token> returned = unitUnderTest.findByName(NAME);
			// Check
			assertSame(token0, returned.get());
		}
	}
}
