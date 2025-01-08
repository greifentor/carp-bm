package de.ollie.carp.bm.gui.mapper;

import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.gui.factory.ImageIconFactory;
import de.ollie.carp.bm.gui.go.TokenGO;
import jakarta.inject.Inject;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class TokenGOMapper {

	@Inject
	protected ImageIconFactory imageIconFactory;

	@Mapping(target = "image", expression = "java(imageIconFactory.create(model.getImage()))")
	public abstract TokenGO toGO(Token model);
}
