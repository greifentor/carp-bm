package de.ollie.carp.bm.rest.v1.mapper;

import de.ollie.carp.bm.client.v1.dto.BattleMapDTO;
import de.ollie.carp.bm.core.model.BattleMap;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BattleMapDTOMapper {
	BattleMapDTO toDTO(BattleMap model);

	BattleMap toModel(BattleMapDTO dto);

	List<BattleMapDTO> toDTOList(List<BattleMap> models);
}
