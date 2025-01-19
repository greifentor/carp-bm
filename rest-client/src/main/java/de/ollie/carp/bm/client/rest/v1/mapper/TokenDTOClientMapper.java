package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.core.model.DnDToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.rest.v1.dto.DnDTokenDTO;
import de.ollie.carp.bm.rest.v1.dto.TokenDTO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface TokenDTOClientMapper {
	@SubclassMapping(source = DnDToken.class, target = DnDTokenDTO.class)
	TokenDTO toDTO(Token model);

	@Named("TokenDTOToModel")
	@SubclassMapping(source = DnDTokenDTO.class, target = DnDToken.class)
	Token toModel(TokenDTO dto);

	@IterableMapping(qualifiedByName = "TokenDTOToModel")
	List<Token> toModels(List<TokenDTO> dto);
}
