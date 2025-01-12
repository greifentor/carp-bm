package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.core.model.DnDTokenData;
import de.ollie.carp.bm.core.model.TokenData;
import de.ollie.carp.bm.rest.v1.dto.DnDTokenDataDTO;
import de.ollie.carp.bm.rest.v1.dto.TokenDataDTO;
import java.util.List;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.SubclassMapping;

@Mapper(componentModel = "spring")
public interface TokenDataDTOClientMapper {
	@Named("TokenDataDTOToModel")
	@SubclassMapping(source = DnDTokenDataDTO.class, target = DnDTokenData.class)
	TokenData toModel(TokenDataDTO dto);

	@IterableMapping(qualifiedByName = "TokenDataDTOToModel")
	List<TokenData> toModels(List<TokenDataDTO> dto);
}
