package de.ollie.carp.maps.rest.api.rest.v1.mapper;

import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.rest.v1.dto.TokenDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDTOMapper extends DTOMapper<Token, TokenDTO> {}
