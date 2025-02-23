package de.ollie.carp.bm.core.model;

import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@ToString(exclude = { "token" })
public class TokenSelection {

	private Token token;
	private boolean selected;
}
