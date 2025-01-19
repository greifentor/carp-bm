package de.ollie.carp.bm.core.model;

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
public class DnDBattleMapToken extends BattleMapToken {

	private int rkCurrent;
	private int tpCurrent;
}
