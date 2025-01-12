package de.ollie.carp.bm.persistence.mapper;

import de.ollie.carp.bm.core.model.DnDTokenData;
import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.persistence.entity.DnDTokenDataDBO;
import de.ollie.carp.bm.persistence.entity.TokenDataDBO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface TokenDataDBOMapper extends DBOMapper<TokenData, TokenDataDBO> {
	@SubclassMapping(source = DnDTokenData.class, target = DnDTokenDataDBO.class)
	@Override
	TokenDataDBO toDBO(TokenData model);

	@Named("TokenDataDBOToModel")
	@Override
	@SubclassMapping(source = DnDTokenDataDBO.class, target = DnDTokenData.class)
	TokenData toModel(TokenDataDBO dbo);

	@IterableMapping(qualifiedByName = "TokenDataDBOToModel")
	@Override
	List<TokenData> toModels(List<TokenDataDBO> dbos);
}
