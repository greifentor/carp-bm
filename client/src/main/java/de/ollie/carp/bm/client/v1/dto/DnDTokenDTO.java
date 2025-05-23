package de.ollie.carp.bm.client.v1.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@EqualsAndHashCode(callSuper = true)
public class DnDTokenDTO extends TokenDTO {

	private int initiativeBonus;
	private int rk;
	private int tpMaximum;
	private DnDTokenSizeDTO tokenSize;

	@Override
	public String toString() {
		return getName() + " (RK " + getRk() + ", TP " + getTpMaximum() + ")";
	}
}
