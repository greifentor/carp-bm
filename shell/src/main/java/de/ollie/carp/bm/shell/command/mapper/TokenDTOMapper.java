package de.ollie.carp.bm.shell.command.mapper;

import de.ollie.carp.bm.client.v1.dto.TokenDTO;

public interface TokenDTOMapper {
	TokenDTO toDto(de.ollie.carp.maps.client.v1.dto.TokenDTO mapsTokenDto);
}
