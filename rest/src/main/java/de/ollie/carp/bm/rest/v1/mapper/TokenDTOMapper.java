package de.ollie.carp.bm.rest.v1.mapper;

import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.rest.v1.dto.TokenOutgoingDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDTOMapper {
	TokenOutgoingDTO toOutgoingDTO(Token model);
}
