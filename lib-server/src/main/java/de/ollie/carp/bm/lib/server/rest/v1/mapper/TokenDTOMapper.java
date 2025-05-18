package de.ollie.carp.bm.lib.server.rest.v1.mapper;

import de.ollie.carp.bm.lib.server.core.model.Token;
import de.ollie.carp.bm.lib.server.rest.v1.dto.TokenDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDTOMapper extends DTOMapper<Token, TokenDTO> {}
