package de.ollie.carp.bm.gui.mapper;

import de.ollie.carp.bm.client.v1.dto.BattleMapDTO;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import de.ollie.carp.bm.gui.go.BattleMapGO;
import jakarta.inject.Inject;
import lombok.Generated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
@Generated
public abstract class BattleMapGOMapper {

	@Inject
	protected ImageIconFactory imageIconFactory;

	@Mapping(target = "image", expression = "java(imageIconFactory.create(dto.getImage()))")
	public abstract BattleMapGO toGO(BattleMapDTO dto);
}
