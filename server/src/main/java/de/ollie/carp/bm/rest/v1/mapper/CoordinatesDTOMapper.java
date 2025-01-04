package de.ollie.carp.bm.rest.v1.mapper;

import de.ollie.carp.bm.core.model.Coordinates;
import de.ollie.carp.bm.rest.v1.dto.CoordinatesDTO;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoordinatesDTOMapper {
	CoordinatesDTO toDTO(Coordinates model);

	Coordinates toModel(CoordinatesDTO dto);

	List<CoordinatesDTO> toDTOList(List<Coordinates> models);
}
