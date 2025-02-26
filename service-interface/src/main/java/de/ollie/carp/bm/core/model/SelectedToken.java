package de.ollie.carp.bm.core.model;

import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
public class SelectedToken {

	private UUID id;
	private BattleMap battleMap;
	private Token token;
}
