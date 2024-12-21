package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.persistence.entity.BattleMapDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BattleMapDBOMapper extends DBOMapper<BattleMap, BattleMapDBO> {}
