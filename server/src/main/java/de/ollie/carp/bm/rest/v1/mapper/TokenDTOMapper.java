package de.ollie.carp.bm.rest.v1.mapper;

import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDTOMapper {
	TokenDTO toDTO(Token model);

	Token toModel(TokenDTO dto);

	List<TokenDTO> toDTOList(List<Token> models);
}
