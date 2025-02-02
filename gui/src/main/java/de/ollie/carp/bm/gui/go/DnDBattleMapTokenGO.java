package de.ollie.carp.bm.gui.go;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DnDBattleMapTokenGO extends BattleMapTokenGO {

	private int rkCurrent;
	private int tpCurrent;
}
