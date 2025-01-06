package de.ollie.carp.bm.core.model;

import java.math.BigDecimal;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class Coordinates {

	private BigDecimal fieldX;
	private BigDecimal fieldY;
}
