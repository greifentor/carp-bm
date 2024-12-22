package de.ollie.carp.bm.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.persistence.entity.BattleMapDBO;
import de.ollie.carp.bm.persistence.entity.BattleMapTokenDBO;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import de.ollie.carp.bm.persistence.factory.BattleMapTokenDBOFactory;
import de.ollie.carp.bm.persistence.mapper.BattleMapDBOMapper;
import de.ollie.carp.bm.persistence.mapper.BattleMapTokenDBOMapper;
import de.ollie.carp.bm.persistence.mapper.TokenDBOMapper;
import de.ollie.carp.bm.persistence.repository.BattleMapTokenDBORepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BattleMapTokenJPAPersistenceAdapterTest {

	private static final int X = 42;
	private static final int Y = 1701;

	@Mock
	private BattleMap battleMap;

	@Mock
	private BattleMapDBO battleMapDBO;

	@Mock
	private BattleMapToken battleMapToken0;

	@Mock
	private BattleMapToken battleMapToken1;

	@Mock
	private BattleMapTokenDBO battleMapTokenDBO0;

	@Mock
	private BattleMapTokenDBO battleMapTokenDBO1;

	@Mock
	private Coordinates coordinates;

	@Mock
	private Token token;

	@Mock
	private TokenDBO tokenDBO;

	@Mock
	private BattleMapDBOMapper battleMapMapper;

	@Mock
	private BattleMapTokenDBOFactory factory;

	@Mock
	private BattleMapTokenDBOMapper mapper;

	@Mock
	private TokenDBOMapper tokenMapper;

	@Mock
	private BattleMapTokenDBORepository repository;

	@InjectMocks
	private BattleMapTokenJPAPersistenceAdapter unitUnderTest;

	@Nested
	class TestsOfMethod_addTokenToBattleMap_Token_BattleMap_Coordinates {

		@Test
		void throwsAnException_passingBattleMapAsNullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.addTokenToBattleMap(token, null, coordinates));
		}

		@Test
		void throwsAnException_passingCoordinatesAsNullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.addTokenToBattleMap(token, battleMap, null));
		}

		@Test
		void throwsAnException_passingTokenAsNullValue() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.addTokenToBattleMap(null, battleMap, coordinates)
			);
		}

		@Test
		void returnsANewBattleMapToken() {
			// Prepare
			when(battleMapMapper.toDBO(battleMap)).thenReturn(battleMapDBO);
			when(tokenMapper.toDBO(token)).thenReturn(tokenDBO);
			when(factory.create(battleMapDBO, tokenDBO)).thenReturn(battleMapTokenDBO0);
			when(repository.save(battleMapTokenDBO0)).thenReturn(battleMapTokenDBO1);
			when(mapper.toModel(battleMapTokenDBO1)).thenReturn(battleMapToken0);
			// Run
			BattleMapToken returned = unitUnderTest.addTokenToBattleMap(token, battleMap, coordinates);
			// Check
			assertNotNull(returned);
		}

		@Test
		void returnsANewBattleMapToken_withXCoordinateSet() {
			// Prepare
			when(coordinates.getX()).thenReturn(X);
			when(coordinates.getY()).thenReturn(Y);
			when(battleMapMapper.toDBO(battleMap)).thenReturn(battleMapDBO);
			when(tokenMapper.toDBO(token)).thenReturn(tokenDBO);
			when(factory.create(battleMapDBO, tokenDBO)).thenReturn(battleMapTokenDBO0);
			when(repository.save(battleMapTokenDBO0)).thenReturn(battleMapTokenDBO1);
			when(mapper.toModel(battleMapTokenDBO1)).thenReturn(battleMapToken0);
			// Run
			unitUnderTest.addTokenToBattleMap(token, battleMap, coordinates);
			// Check
			verify(battleMapTokenDBO0, times(1)).setPositionX(X);
		}

		@Test
		void returnsANewBattleMapToken_withYCoordinateSet() {
			// Prepare
			when(coordinates.getX()).thenReturn(X);
			when(coordinates.getY()).thenReturn(Y);
			when(battleMapMapper.toDBO(battleMap)).thenReturn(battleMapDBO);
			when(tokenMapper.toDBO(token)).thenReturn(tokenDBO);
			when(factory.create(battleMapDBO, tokenDBO)).thenReturn(battleMapTokenDBO0);
			when(repository.save(battleMapTokenDBO0)).thenReturn(battleMapTokenDBO1);
			when(mapper.toModel(battleMapTokenDBO1)).thenReturn(battleMapToken0);
			// Run
			unitUnderTest.addTokenToBattleMap(token, battleMap, coordinates);
			// Check
			verify(battleMapTokenDBO0, times(1)).setPositionY(Y);
		}
	}

	@Nested
	class TestsOfMethod_save_BattleMapToken {

		@Test
		void throwsAnException_passingANullValueAsBattleMapToken() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.save(null));
		}

		@Test
		void returnsTheObjectReturnedByTheRepositoryCall() {
			// Prepare
			when(mapper.toDBO(battleMapToken0)).thenReturn(battleMapTokenDBO0);
			when(repository.save(battleMapTokenDBO0)).thenReturn(battleMapTokenDBO1);
			when(mapper.toModel(battleMapTokenDBO1)).thenReturn(battleMapToken1);
			// Run
			BattleMapToken returned = unitUnderTest.save(battleMapToken0);
			// Check
			assertSame(battleMapToken1, returned);
		}
	}
}
