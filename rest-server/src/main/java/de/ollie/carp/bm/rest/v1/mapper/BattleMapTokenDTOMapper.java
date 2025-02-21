package de.ollie.carp.bm.rest.v1.mapper;

import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.DnDBattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.core.model.BattleMapToken;
import de.ollie.carp.bm.core.model.DnDBattleMapToken;
import de.ollie.carp.bm.core.model.DnDToken;
import de.ollie.carp.bm.core.model.Token;
import jakarta.inject.Inject;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", uses = { BattleMapDTOMapper.class })
public abstract class BattleMapTokenDTOMapper {

	@Inject
	private TokenDTOMapper tokenDTOMapper;

	@Named("BattleMapTokenToDTO")
	@SubclassMapping(source = DnDBattleMapToken.class, target = DnDBattleMapTokenDTO.class)
	@Mapping(target = "token", source = "token", qualifiedByName = "mapTokenDTO")
	public abstract BattleMapTokenDTO toDTO(BattleMapToken model);

	@Named("mapTokenDTO")
	protected TokenDTO map(Token model) {
		if (model instanceof DnDToken dndModel) {
			return tokenDTOMapper.toDTO(dndModel);
		}
		return tokenDTOMapper.toDTO(model);
	}

	@SubclassMapping(source = DnDBattleMapTokenDTO.class, target = DnDBattleMapToken.class)
	public abstract BattleMapToken toModel(BattleMapTokenDTO dto);

	@IterableMapping(qualifiedByName = "BattleMapTokenToDTO")
	public abstract List<BattleMapTokenDTO> toDTOList(List<BattleMapToken> models);
}
