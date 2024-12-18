package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.Spielrunde;
import de.ollie.carp.bm.persistence.entity.SpielrundeDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpielrundeDBOMapper extends DBOMapper<Spielrunde, SpielrundeDBO> {}
