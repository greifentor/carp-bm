package de.ollie.carp.bm.rest.v1.mapper;

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
public interface BattleMapTokenDTOMapper {
	@Named("BattleMapTokenToDTO")
	@SubclassMapping(source = DnDBattleMapToken.class, target = DnDBattleMapTokenDTO.class)
	BattleMapTokenDTO toDTO(BattleMapToken model);

	@SubclassMapping(source = DnDBattleMapTokenDTO.class, target = DnDBattleMapToken.class)
	BattleMapToken toModel(BattleMapTokenDTO dto);

	@IterableMapping(qualifiedByName = "BattleMapTokenToDTO")
	List<BattleMapTokenDTO> toDTOList(List<BattleMapToken> models);
}
