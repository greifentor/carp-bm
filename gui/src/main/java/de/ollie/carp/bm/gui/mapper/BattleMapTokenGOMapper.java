package de.ollie.carp.bm.gui.mapper;

import de.ollie.carp.bm.client.v1.dto.BattleMapTokenDTO;
import de.ollie.carp.bm.client.v1.dto.DnDBattleMapTokenDTO;
import de.ollie.carp.bm.gui.go.BattleMapTokenGO;
import de.ollie.carp.bm.gui.go.DnDBattleMapTokenGO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring", uses = { BattleMapGOMapper.class, TokenGOMapper.class })
public interface BattleMapTokenGOMapper {
	@Named("BattleMapTokenGOToModel")
	@SubclassMapping(source = DnDBattleMapTokenDTO.class, target = DnDBattleMapTokenGO.class)
	BattleMapTokenGO toGO(BattleMapTokenDTO model);

	@IterableMapping(qualifiedByName = "BattleMapTokenGOToModel")
	List<BattleMapTokenGO> toGOs(List<BattleMapTokenDTO> model);
}
