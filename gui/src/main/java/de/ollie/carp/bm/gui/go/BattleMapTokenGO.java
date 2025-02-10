package de.ollie.carp.bm.gui.go;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class BattleMapTokenGO {

	private UUID id;
	private BattleMapGO battleMap;
	private TokenGO token;
	private BigDecimal fieldX;
	private BigDecimal fieldY;

	public CoordinatesXYGO getTokenLeftUpperCorner() {
		return new CoordinatesXYGO()
			.setX(
				(int) (getBattleMap().getFieldSizeInPixels() * getFieldX().doubleValue()) + getBattleMap().getOffsetInPixels()
			)
			.setY(
				(int) (getBattleMap().getFieldSizeInPixels() * getFieldY().doubleValue()) + getBattleMap().getOffsetInPixels()
			);
	}
}
