package de.ollie.carp.bm.client.rest.v1.mapper;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.rest.v1.dto.CoordinatesDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordinatesDTOClientMapper {
	Coordinates toModel(CoordinatesDTO dto);

	List<Coordinates> toModels(List<CoordinatesDTO> dto);
}
