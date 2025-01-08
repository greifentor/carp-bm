package de.ollie.carp.bm.gui.go;

import de.ollie.carp.bm.core.model.CoordinatesXY;
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

	public CoordinatesXY getTokenLeftUpperCorner() {
		return new CoordinatesXY()
			.setX(
				(int) (getBattleMap().getFieldSizeInPixels() * getFieldX().doubleValue()) + getBattleMap().getOffsetInPixels()
			)
			.setY(
				(int) (getBattleMap().getFieldSizeInPixels() * getFieldY().doubleValue()) + getBattleMap().getOffsetInPixels()
			);
	}
}
