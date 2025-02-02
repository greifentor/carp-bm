package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.DnDBattleMapToken;
import de.ollie.carp.bm.persistence.entity.BattleMapTokenDBO;
import de.ollie.carp.bm.persistence.entity.DnDBattleMapTokenDBO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", uses = { BattleMapDBOMapper.class, TokenDBOMapper.class })
public interface BattleMapTokenDBOMapper extends DBOMapper<BattleMapToken, BattleMapTokenDBO> {
	@SubclassMapping(source = DnDBattleMapToken.class, target = DnDBattleMapTokenDBO.class)
	@Override
	BattleMapTokenDBO toDBO(BattleMapToken model);

	@Named("BattleMapTokenDBOToModel")
	@Override
	@SubclassMapping(source = DnDBattleMapTokenDBO.class, target = DnDBattleMapToken.class)
	BattleMapToken toModel(BattleMapTokenDBO dbo);

	@IterableMapping(qualifiedByName = "BattleMapTokenDBOToModel")
	@Override
	List<BattleMapToken> toModels(List<BattleMapTokenDBO> dbos);
}
