package de.ollie.carp.bm.gui.mapper;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = { BattleMapGOMapper.class, TokenGOMapper.class })
public interface BattleMapTokenGOMapper {
	BattleMapTokenGO toGO(BattleMapToken model);

	List<BattleMapTokenGO> toGOs(List<BattleMapToken> model);
}
