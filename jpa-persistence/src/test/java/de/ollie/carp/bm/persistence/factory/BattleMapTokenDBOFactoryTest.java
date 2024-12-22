package de.ollie.carp.bm.persistence.factory;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.persistence.entity.BattleMapDBO;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BattleMapTokenDBOFactoryTest {

	private static final UUID UID = UUID.randomUUID();

	@Mock
	private BattleMapDBO battleMap;

	@Mock
	private TokenDBO token;

	@Mock
	private UUIDFactory uuidFactory;

	@InjectMocks
	private BattleMapTokenDBOFactory unitUnderTest;

	@Test
	void throwsAnException_passingANullValueAsBattleMap() {
		assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(null, token));
	}

	@Test
	void throwsAnException_passingANullValueAsToken() {
		assertThrows(IllegalArgumentException.class, () -> unitUnderTest.create(battleMap, null));
	}

	@Test
	void returnsABattleMapTokenDBO() {
		assertNotNull(unitUnderTest.create(battleMap, token));
	}

	@Test
	void returnsABattleMapTokenDBO_withSetId() {
		when(uuidFactory.create()).thenReturn(UID);
		assertSame(UID, unitUnderTest.create(battleMap, token).getId());
	}

	@Test
	void returnsABattleMapTokenDBO_withPassedBattleMap() {
		assertSame(battleMap, unitUnderTest.create(battleMap, token).getBattleMap());
	}

	@Test
	void returnsABattleMapTokenDBO_withPassedToken() {
		assertSame(token, unitUnderTest.create(battleMap, token).getToken());
	}

	@Test
	void returnsANewBattleMapTokenDBO_onEachCall() {
		assertNotSame(unitUnderTest.create(battleMap, token), unitUnderTest.create(battleMap, token));
	}
}
