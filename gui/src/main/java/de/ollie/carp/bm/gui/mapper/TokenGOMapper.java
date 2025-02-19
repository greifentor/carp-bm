package de.ollie.carp.bm.gui.mapper;

import de.ollie.carp.bm.client.v1.dto.DnDTokenDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.gui.go.DnDTokenGO;
import de.ollie.carp.bm.gui.go.TokenGO;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
@Generated
public interface TokenGOMapper {
	@Named("BattleMapTokenGOToModel")
	@SubclassMapping(source = DnDTokenDTO.class, target = DnDTokenGO.class)
	TokenGO toGO(TokenDTO model);
}
