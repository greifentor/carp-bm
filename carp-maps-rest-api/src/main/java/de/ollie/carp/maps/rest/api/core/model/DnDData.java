package de.ollie.carp.maps.rest.api.core.model;

import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class DnDData {

	private int initiativeBonus;
	private int rk;
	private int tpMaximum;
}
