package de.ollie.carp.maps.rest.api.persistence.mapper;

import de.ollie.carp.maps.rest.api.core.model.DnDData;
import de.ollie.carp.maps.rest.api.core.model.Regelsystem;
import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.persistence.entity.DndImageTokenDBO;
import de.ollie.carp.maps.rest.api.persistence.entity.ImageDBO;
import jakarta.inject.Named;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class ImageDBO2TokenMapper implements DBOMapper<Token, ImageDBO> {

	private final SitzungTypDBO2RegelsystemMapper sitzungTypMapper;
	private final SpielwerteParser spielwerteParser;
	private final SubHeaderParser subHeaderParser;

	@Override
	public Token toModel(ImageDBO dbo) {
		if (dbo instanceof DndImageTokenDBO dndDbo) {
			return new Token()
				.setDndData(
					new DnDData()
						.setInitiativeBonus(dndDbo.getInitiativeBonus())
						.setRk(spielwerteParser.getRk(dndDbo.getSpielwerte()))
						.setTokenSize(subHeaderParser.getTokenSize(dndDbo.getSubHeader()))
						.setTpMaximum(dndDbo.getMaxTp())
				)
				.setId(UUID.fromString(dbo.getGlobalId()))
				.setImage(dbo.getImage())
				.setName(dbo.getName())
				.setRegelsystem(Regelsystem.DND);
		}
		return new Token()
			.setId(UUID.fromString(dbo.getGlobalId()))
			.setImage(dbo.getImage())
			.setName(dbo.getName())
			.setRegelsystem(sitzungTypMapper.map(dbo.getSitzungTyp()));
	}
}
