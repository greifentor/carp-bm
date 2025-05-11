package de.ollie.carp.bm.shell.command.mapper.impl;

import de.ollie.carp.bm.client.v1.dto.DnDTokenDTO;
import de.ollie.carp.bm.client.v1.dto.DnDTokenSizeDTO;
import de.ollie.carp.bm.client.v1.dto.ShapeTypeDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.shell.command.mapper.TokenDTOMapper;
import jakarta.inject.Named;

@Named
class TokenDTOMapperImpl implements TokenDTOMapper {

	@Override
	public TokenDTO toDto(de.ollie.carp.maps.client.v1.dto.TokenDTO mapsTokenDto) {
		if (mapsTokenDto == null) {
			return null;
		}
		if (mapsTokenDto.getDndData() != null) {
			return new DnDTokenDTO()
				.setInitiativeBonus(mapsTokenDto.getDndData().getInitiativeBonus())
				.setRk(mapsTokenDto.getDndData().getRk())
				.setTokenSize(DnDTokenSizeDTO.MITTEL)
				.setTpMaximum(mapsTokenDto.getDndData().getTpMaximum())
				.setId(mapsTokenDto.getId())
				.setImage(mapsTokenDto.getImage())
				.setName(mapsTokenDto.getName())
				.setShapeType(ShapeTypeDTO.CIRCLE);
		}
		return new TokenDTO()
			.setId(mapsTokenDto.getId())
			.setImage(mapsTokenDto.getImage())
			.setName(mapsTokenDto.getName())
			.setShapeType(ShapeTypeDTO.CIRCLE);
	}
}
