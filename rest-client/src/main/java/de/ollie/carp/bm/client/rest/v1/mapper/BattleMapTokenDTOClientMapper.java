package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.DnDBattleMapToken;
import de.ollie.carp.bm.rest.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.rest.v1.dto.DnDBattleMapTokenDTO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface BattleMapTokenDTOClientMapper {
	@Named("BattleMapTokenDTOToModel")
	@SubclassMapping(source = DnDBattleMapTokenDTO.class, target = DnDBattleMapToken.class)
	BattleMapToken toModel(BattleMapTokenDTO dto);

	@IterableMapping(qualifiedByName = "BattleMapTokenDTOToModel")
	List<BattleMapToken> toModels(List<BattleMapTokenDTO> dto);
}
