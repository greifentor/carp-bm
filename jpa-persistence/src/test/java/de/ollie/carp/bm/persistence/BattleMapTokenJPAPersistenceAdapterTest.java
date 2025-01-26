package de.ollie.carp.bm.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.BattleMapTokenData;
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
import java.math.BigDecimal;
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
class BattleMapTokenJPAPersistenceAdapterTest {

	private static final UUID UID = UUID.randomUUID();
	private static final BigDecimal X = new BigDecimal(42);
	private static final BigDecimal Y = new BigDecimal(1701);

	@Mock
	private BattleMap battleMap;

	@Mock
	private BattleMapDBO battleMapDBO;

	@Mock
	private BattleMapToken battleMapToken0;

	@Mock
	private BattleMapToken battleMapToken1;

	@Mock
	private BattleMapTokenData battleMapTokenData;

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
	class addTokenToBattleMap_Token_BattleMap_Coordinates {

		@Test
		void throwsAnException_passingBattleMapAsNullValue() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.addTokenToBattleMap(token, null, battleMapTokenData)
			);
		}

		@Test
		void throwsAnException_passingBattleMapTokenDataAsNullValue() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.addTokenToBattleMap(token, battleMap, null));
		}

		@Test
		void throwsAnException_passingTokenAsNullValue() {
			assertThrows(
				IllegalArgumentException.class,
				() -> unitUnderTest.addTokenToBattleMap(null, battleMap, battleMapTokenData)
			);
		}

		@Test
		void returnsANewBattleMapToken() {
			// Prepare
			when(coordinates.getFieldX()).thenReturn(X);
			when(coordinates.getFieldY()).thenReturn(Y);
			when(battleMapMapper.toDBO(battleMap)).thenReturn(battleMapDBO);
			when(battleMapTokenData.getCoordinates()).thenReturn(coordinates);
			when(tokenMapper.toDBO(token)).thenReturn(tokenDBO);
			when(factory.create(battleMapDBO, tokenDBO)).thenReturn(battleMapTokenDBO0);
			when(repository.save(battleMapTokenDBO0)).thenReturn(battleMapTokenDBO1);
			when(mapper.toModel(battleMapTokenDBO1)).thenReturn(battleMapToken0);
			// Run
			BattleMapToken returned = unitUnderTest.addTokenToBattleMap(token, battleMap, battleMapTokenData);
			// Check
			assertNotNull(returned);
		}

		@Test
		void returnsANewBattleMapToken_withXCoordinateSet() {
			// Prepare
			when(coordinates.getFieldX()).thenReturn(X);
			when(coordinates.getFieldY()).thenReturn(Y);
			when(battleMapMapper.toDBO(battleMap)).thenReturn(battleMapDBO);
			when(battleMapTokenData.getCoordinates()).thenReturn(coordinates);
			when(tokenMapper.toDBO(token)).thenReturn(tokenDBO);
			when(factory.create(battleMapDBO, tokenDBO)).thenReturn(battleMapTokenDBO0);
			when(repository.save(battleMapTokenDBO0)).thenReturn(battleMapTokenDBO1);
			when(mapper.toModel(battleMapTokenDBO1)).thenReturn(battleMapToken0);
			// Run
			unitUnderTest.addTokenToBattleMap(token, battleMap, battleMapTokenData);
			// Check
			verify(battleMapTokenDBO0, times(1)).setFieldX(X);
		}

		@Test
		void returnsANewBattleMapToken_withYCoordinateSet() {
			// Prepare
			when(coordinates.getFieldX()).thenReturn(X);
			when(coordinates.getFieldY()).thenReturn(Y);
			when(battleMapMapper.toDBO(battleMap)).thenReturn(battleMapDBO);
			when(battleMapTokenData.getCoordinates()).thenReturn(coordinates);
			when(tokenMapper.toDBO(token)).thenReturn(tokenDBO);
			when(factory.create(battleMapDBO, tokenDBO)).thenReturn(battleMapTokenDBO0);
			when(repository.save(battleMapTokenDBO0)).thenReturn(battleMapTokenDBO1);
			when(mapper.toModel(battleMapTokenDBO1)).thenReturn(battleMapToken0);
			// Run
			unitUnderTest.addTokenToBattleMap(token, battleMap, battleMapTokenData);
			// Check
			verify(battleMapTokenDBO0, times(1)).setFieldY(Y);
		}
	}

	@Nested
	class findById_UUID {

		@Test
		void throwsAnException_passingANullValueAsId() {
			assertThrows(IllegalArgumentException.class, () -> unitUnderTest.findById(null));
		}

		@Test
		void returnsTheCorrectlyMappedObjectReadFromRepository() {
			// Prepare
			Optional<BattleMapToken> expected = Optional.of(battleMapToken0);
			when(mapper.toModel(battleMapTokenDBO0)).thenReturn(battleMapToken0);
			when(repository.findById(UID)).thenReturn(Optional.of(battleMapTokenDBO0));
			// Run & Check
			assertEquals(expected, unitUnderTest.findById(UID));
		}
	}

	@Nested
	class findAllByBattleMap_BattleMap {

		@Test
		void returnsTheMappedObjectReturnedByThePersistencePort() {
			// Prepare
			List<BattleMapToken> expected = List.of(battleMapToken0);
			List<BattleMapTokenDBO> dbos = List.of(battleMapTokenDBO0);
			when(battleMapMapper.toDBO(battleMap)).thenReturn(battleMapDBO);
			when(mapper.toModels(dbos)).thenReturn(expected);
			when(repository.findAllByBattleMap(battleMapDBO)).thenReturn(dbos);
			// Run
			List<BattleMapToken> returned = unitUnderTest.findAllByBattleMap(battleMap);
			// Check
			assertSame(expected, returned);
		}
	}

	@Nested
	class save_BattleMapToken {

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
