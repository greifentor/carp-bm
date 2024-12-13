package de.ollie.carp.bm.rest.v1.mapper;

import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.rest.v1.dto.TokenResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDTOMapper {
	TokenResponseDTO toResponseDTO(Token model);
}
