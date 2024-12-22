package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.persistence.entity.BattleMapTokenDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BattleMapTokenDBOMapper extends DBOMapper<BattleMapToken, BattleMapTokenDBO> {}
