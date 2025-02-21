package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.DnDBattleMapToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.persistence.entity.BattleMapTokenDBO;
import de.ollie.carp.bm.persistence.entity.DnDBattleMapTokenDBO;
import de.ollie.carp.bm.persistence.entity.DnDTokenDBO;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import jakarta.inject.Inject;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public abstract class BattleMapTokenDBOMapper implements DBOMapper<BattleMapToken, BattleMapTokenDBO> {

	@Inject
	private TokenDBOMapper tokenDBOMapper;

	@SubclassMapping(source = DnDBattleMapToken.class, target = DnDBattleMapTokenDBO.class)
	@Override
	public abstract BattleMapTokenDBO toDBO(BattleMapToken model);

	@Named("BattleMapTokenDBOToModel")
	@Override
	@SubclassMapping(source = DnDBattleMapTokenDBO.class, target = DnDBattleMapToken.class)
	@Mapping(target = "token", source = "token", qualifiedByName = "mapTokenDBO")
	public abstract BattleMapToken toModel(BattleMapTokenDBO dbo);

	@Named("mapTokenDBO")
	protected Token map(TokenDBO dbo) {
		if (dbo instanceof DnDTokenDBO dndDBO) {
			return tokenDBOMapper.toModel(dndDBO);
		}
		return tokenDBOMapper.toModel(dbo);
	}

	@IterableMapping(qualifiedByName = "BattleMapTokenDBOToModel")
	@Override
	public abstract List<BattleMapToken> toModels(List<BattleMapTokenDBO> dbos);
}
