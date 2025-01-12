package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TokenDTOClientMapper {
	TokenDTO toDTO(Token model);

	Token toModel(TokenDTO dto);

	List<Token> toModels(List<TokenDTO> dto);
}
