package de.ollie.carp.maps.rest.api.rest.v1.dto;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class DndDataDTO {

	private int initiativeBonus;
	private int rk;
	private DnDTokenSizeDTO tokenSize;
	private int tpMaximum;
}
