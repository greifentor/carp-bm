package de.ollie.carp.bm.gui.go;

import java.util.List;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class HitsGO {

	private int fieldX;
	private int fieldY;
	private List<BattleMapTokenGO> battleMapTokens = List.of();
}
