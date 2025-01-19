package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.DnDToken;
import de.ollie.carp.bm.core.model.Token;
import de.ollie.carp.bm.persistence.entity.DnDTokenDBO;
import de.ollie.carp.bm.persistence.entity.TokenDBO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface TokenDBOMapper extends DBOMapper<Token, TokenDBO> {
	@SubclassMapping(source = DnDToken.class, target = DnDTokenDBO.class)
	@Override
	TokenDBO toDBO(Token model);

	@Named("TokenDBOToModel")
	@Override
	@SubclassMapping(source = DnDTokenDBO.class, target = DnDToken.class)
	Token toModel(TokenDBO dbo);

	@IterableMapping(qualifiedByName = "TokenDBOToModel")
	@Override
	List<Token> toModels(List<TokenDBO> dbos);
}
