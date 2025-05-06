package de.ollie.carp.maps.rest.api.persistence.mapper;

import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.persistence.entity.ImageDBO;
import jakarta.inject.Named;
import java.util.UUID;

@Named
public class ImageDBO2TokenMapper implements DBOMapper<Token, ImageDBO> {

	@Override
	public Token toModel(ImageDBO dbo) {
		return new Token().setId(UUID.fromString(dbo.getGlobalId())).setImage(dbo.getImage()).setName(dbo.getName());
	}
}
