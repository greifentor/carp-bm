package de.ollie.carp.bm.rest.v1.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class BattleMapTokenDTO {

	private UUID id;
	private BattleMapDTO battleMap;
	private TokenDTO token;
	private BigDecimal fieldX;
	private BigDecimal fieldY;
}
