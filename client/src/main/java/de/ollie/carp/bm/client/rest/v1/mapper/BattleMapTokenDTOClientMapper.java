package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.rest.v1.dto.BattleMapTokenDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BattleMapTokenDTOClientMapper {
	BattleMapToken toModel(BattleMapTokenDTO dto);

	List<BattleMapToken> toModels(List<BattleMapTokenDTO> dto);
}
