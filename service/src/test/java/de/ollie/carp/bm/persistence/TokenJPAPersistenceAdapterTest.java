package de.ollie.carp.bm.persistence;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.exception.UniqueConstraintViolationException;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import de.ollie.carp.bm.persistence.factory.TokenDBOFactory;
import de.ollie.carp.bm.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.bm.persistence.repository.TokenDBORepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TokenJPAPersistenceAdapterTest {

	private static final String NAME = "name";

	@Mock
	private Token token;

	@Mock
	private TokenDBO tokenDBO;

	@Mock
	private TokenDBOFactory tokenDBOFactory;

	@Mock
	private TokenDBOMapper tokenDBOMapper;

	@Mock
	private TokenDBORepository tokenDBORepository;

	@InjectMocks
	private TokenJPAPersistenceAdapter unitUnderTest;

	@Nested
	class TestsOfTheMethod_createTokenWithName_String {

		@Test
		void throwsAnException_passingAnAlreadyExistingName() {
			// Prepare
			when(tokenDBORepository.findByName(NAME)).thenReturn(Optional.of(tokenDBO));
			// Run & Check
			assertThrows(UniqueConstraintViolationException.class, () -> unitUnderTest.createTokenWithName(NAME));
			verifyNoMoreInteractions(tokenDBOFactory, tokenDBOMapper, tokenDBORepository);
		}

		@Test
		void returnsANewTokenWithPassedNameAndSetUUID() {
			// Prepare
			when(tokenDBORepository.findByName(NAME)).thenReturn(Optional.empty());
			when(tokenDBOFactory.createWithName(NAME)).thenReturn(tokenDBO);
			when(tokenDBORepository.save(tokenDBO)).thenReturn(tokenDBO);
			when(tokenDBOMapper.toModel(tokenDBO)).thenReturn(token);
			// Run
			Token returned = unitUnderTest.createTokenWithName(NAME);
			// Check
			assertSame(token, returned);
		}
	}

	@Nested
	class TestsOfTheMethod_findAll {

		@Test
		void returnsTokenData_returnedByTheRepositoryMethod() {
			// Prepare
			List<TokenDBO> tokenDBOs = List.of(tokenDBO);
			List<Token> tokens = List.of(token);
			when(tokenDBORepository.findAll()).thenReturn(tokenDBOs);
			when(tokenDBOMapper.toModels(tokenDBOs)).thenReturn(tokens);
			// Run
			List<Token> returned = unitUnderTest.findAll();
			// Check
			assertSame(token, returned);
		}
	}
}
