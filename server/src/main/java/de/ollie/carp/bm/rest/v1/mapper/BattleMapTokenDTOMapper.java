package de.ollie.carp.bm.rest.v1.mapper;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.rest.v1.dto.BattleMapTokenDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BattleMapTokenDTOMapper {
	BattleMapTokenDTO toDTO(BattleMapToken model);

	BattleMapToken toModel(BattleMapTokenDTO dto);

	List<BattleMapTokenDTO> toDTOList(List<BattleMapToken> models);
}
