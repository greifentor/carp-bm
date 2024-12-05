package de.ollie.carp.bm.core.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class Coordinates {

	private int x;
	private int y;
}
