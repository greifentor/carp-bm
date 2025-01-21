package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.core.model.BattleMapTokenData;
import de.ollie.carp.bm.core.model.DnDBattleMapTokenData;
import de.ollie.carp.bm.rest.v1.dto.BattleMapTokenDataDTO;
import de.ollie.carp.bm.rest.v1.dto.DnDBattleMapTokenDataDTO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface BattleMapTokenDataDTOClientMapper {
	@SubclassMapping(source = BattleMapTokenData.class, target = DnDBattleMapTokenDataDTO.class)
	DnDBattleMapTokenDataDTO toDTO(BattleMapTokenData model);

	@Named("BattleMapTokenDataDTOToModel")
	@SubclassMapping(source = DnDBattleMapTokenDataDTO.class, target = DnDBattleMapTokenData.class)
	BattleMapTokenData toModel(BattleMapTokenDataDTO dto);

	@IterableMapping(qualifiedByName = "BattleMapTokenDataDTOToModel")
	List<BattleMapTokenData> toModels(List<BattleMapTokenDataDTO> dto);
}
