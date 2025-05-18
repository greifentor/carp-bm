package de.ollie.carp.bm.lib.server.persistence.mapper;

import de.ollie.carp.bm.lib.server.core.model.Token;
import de.ollie.carp.bm.lib.server.persistence.entity.DBOMapper;
import de.ollie.carp.bm.lib.server.persistence.entity.TokenDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDBOMapper extends DBOMapper<Token, TokenDBO> {}
