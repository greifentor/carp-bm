package de.ollie.carp.maps.rest.api.persistence.mapper;

import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.persistence.entity.ImageDBO;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ImageDBO2TokenMapper implements DBOMapper<Token, ImageDBO> {

	private final SitzungTypDBO2RegelsystemMapper sitzungTypMapper;

	@Override
	public Token toModel(ImageDBO dbo) {
		return new Token()
			.setId(UUID.fromString(dbo.getGlobalId()))
			.setImage(dbo.getImage())
			.setName(dbo.getName())
			.setRegelsystem(sitzungTypMapper.map(dbo.getSitzungTyp()));
	}
}
