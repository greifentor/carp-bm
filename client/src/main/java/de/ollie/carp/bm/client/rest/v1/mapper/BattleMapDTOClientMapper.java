package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.core.model.BattleMap;
import de.ollie.carp.bm.rest.v1.dto.BattleMapDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BattleMapDTOClientMapper {
	BattleMap toModel(BattleMapDTO dto);

	List<BattleMap> toModels(List<BattleMapDTO> dto);
}
