package de.ollie.carp.bm.rest.v1.mapper;

import de.ollie.carp.bm.client.v1.dto.DnDTokenDTO;
import de.ollie.carp.bm.client.v1.dto.TokenDTO;
import de.ollie.carp.bm.core.model.DnDToken;
import de.ollie.carp.bm.core.model.Token;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface TokenDTOMapper {
	@Named("TokenToDTO")
	@SubclassMapping(source = DnDToken.class, target = DnDTokenDTO.class)
	TokenDTO toDTO(Token model);

	@SubclassMapping(source = DnDTokenDTO.class, target = DnDToken.class)
	Token toModel(TokenDTO dto);

	@IterableMapping(qualifiedByName = "TokenToDTO")
	List<TokenDTO> toDTOList(List<Token> models);
}
