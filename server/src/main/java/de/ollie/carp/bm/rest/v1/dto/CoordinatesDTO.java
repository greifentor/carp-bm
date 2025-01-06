package de.ollie.carp.bm.rest.v1.dto;

import java.math.BigDecimal;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class CoordinatesDTO {

	private BigDecimal fieldX;
	private BigDecimal fieldY;
}
