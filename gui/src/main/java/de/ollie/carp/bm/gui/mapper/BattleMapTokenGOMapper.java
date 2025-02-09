package de.ollie.carp.bm.gui.mapper;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.DnDBattleMapToken;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import de.ollie.carp.bm.gui.go.DnDBattleMapTokenGO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", uses = { BattleMapGOMapper.class, TokenGOMapper.class })
public interface BattleMapTokenGOMapper {
	@Named("BattleMapTokenGOToModel")
	@SubclassMapping(source = DnDBattleMapToken.class, target = DnDBattleMapTokenGO.class)
	BattleMapTokenGO toGO(BattleMapToken model);

	@IterableMapping(qualifiedByName = "BattleMapTokenGOToModel")
	List<BattleMapTokenGO> toGOs(List<BattleMapToken> model);
}
