package de.ollie.carp.bm.core.model;

import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@ToString(exclude = { "token" })
public class SelectedToken {

	private BattleMap id;
	private Token token;
}
