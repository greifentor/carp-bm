package de.ollie.carp.maps.rest.api.persistence.mapper;

import de.ollie.carp.maps.rest.api.core.model.Token;
import de.ollie.carp.maps.rest.api.persistence.entity.TokenDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDBOMapper extends DBOMapper<Token, TokenDBO> {}
