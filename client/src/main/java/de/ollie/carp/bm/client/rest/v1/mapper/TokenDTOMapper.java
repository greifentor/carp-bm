package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.client.rest.v1.dto.TokenResponseDTO;
import de.ollie.carp.bm.core.model.Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDTOMapper {
	Token toModel(TokenResponseDTO dto);
}
