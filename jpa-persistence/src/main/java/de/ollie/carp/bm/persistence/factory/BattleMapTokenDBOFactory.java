package de.ollie.carp.bm.persistence.factory;

import static de.ollie.carp.bm.util.Check.ensure;

import de.ollie.carp.bm.core.service.factory.UUIDFactory;
import de.ollie.carp.bm.persistence.entity.BattleMapDBO;
import de.ollie.carp.bm.persistence.entity.BattleMapTokenDBO;
import de.ollie.carp.bm.persistence.entity.DnDBattleMapTokenDBO;
import de.ollie.carp.bm.persistence.entity.DnDTokenDBO;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class BattleMapTokenDBOFactory {

	private final UUIDFactory uuidFactory;

	public BattleMapTokenDBO create(BattleMapDBO battleMap, TokenDBO token) {
		ensure(battleMap != null, "battle map cannot be null!");
		ensure(token != null, "token cannot be null!");
		BattleMapTokenDBO dbo = null;
		if (token instanceof DnDTokenDBO) {
			dbo = new DnDBattleMapTokenDBO();
		} else {
			dbo = new BattleMapTokenDBO();
		}
		return dbo.setBattleMap(battleMap).setId(uuidFactory.create()).setToken(token);
	}
}
