package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.SelectedToken;
import de.ollie.carp.bm.persistence.entity.SelectedTokenDBO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SelectedTokenDBOMapper extends DBOMapper<SelectedToken, SelectedTokenDBO> {}
