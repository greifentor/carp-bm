package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDBOMapper extends DBOMapper<Token, TokenDBO> {}
