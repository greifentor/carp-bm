package de.ollie.carp.bm.gui.mapper;

import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.DnDBattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.DnDTokenDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import de.ollie.carp.bm.gui.go.DnDBattleMapTokenGO;
import de.ollie.carp.bm.gui.go.TokenGO;
import jakarta.inject.Inject;
import java.util.List;
import lombok.Generated;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", uses = { BattleMapGOMapper.class, TokenGOMapper.class })
@Generated
public abstract class BattleMapTokenGOMapper {

	@Inject
	private TokenGOMapper tokenGOMapper;

	@Named("BattleMapTokenGOToModel")
	@SubclassMapping(source = DnDBattleMapTokenDTO.class, target = DnDBattleMapTokenGO.class)
	@Mapping(target = "token", source = "token", qualifiedByName = "mapTokenGO")
	public abstract BattleMapTokenGO toGO(BattleMapTokenDTO dto);

	@Named("mapTokenGO")
	protected TokenGO map(TokenDTO dto) {
		if (dto instanceof DnDTokenDTO dndDTO) {
			return tokenGOMapper.toGO(dndDTO);
		}
		return tokenGOMapper.toGO(dto);
	}

	@IterableMapping(qualifiedByName = "BattleMapTokenGOToModel")
	public abstract List<BattleMapTokenGO> toGOs(List<BattleMapTokenDTO> model);
}
