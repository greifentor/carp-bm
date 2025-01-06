package de.ollie.carp.bm.core.model;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class BattleMapToken {

	private UUID id;
	private BattleMap battleMap;
	private Token token;
	private BigDecimal fieldX;
	private BigDecimal fieldY;
}
