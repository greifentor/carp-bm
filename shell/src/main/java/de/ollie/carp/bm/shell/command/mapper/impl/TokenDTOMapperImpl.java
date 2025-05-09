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
		return new DnDTokenDTO()
			.setRk(10)
			.setTokenSize(DnDTokenSizeDTO.MITTEL)
			.setTpMaximum(42)
			.setId(mapsTokenDto.getId())
			.setImage(mapsTokenDto.getImage())
			.setName(mapsTokenDto.getName())
			.setShapeType(ShapeTypeDTO.CIRCLE);
	}
}
