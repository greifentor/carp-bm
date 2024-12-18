package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.SpielrundeToken;
import de.ollie.carp.bm.persistence.entity.SpielrundeTokenDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpielrundeTokenDBOMapper extends DBOMapper<SpielrundeToken, SpielrundeTokenDBO> {}
