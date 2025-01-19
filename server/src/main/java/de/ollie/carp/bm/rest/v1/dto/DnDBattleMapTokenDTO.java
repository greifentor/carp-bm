package de.ollie.carp.bm.rest.v1.dto;

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
public class DnDBattleMapTokenDTO extends BattleMapTokenDTO {

	private int rkCurrent;
	private int tpCurrent;
}
