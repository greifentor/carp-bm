package de.ollie.carp.bm.lib.server.rest.v1.dto;

import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Generated
@RequiredArgsConstructor
public enum DnDTokenSizeDTO {
	WINZIG(0.5D),
	KLEIN(1.0D),
	MITTEL(1.0D),
	GROSS(2.0D),
	RIESIG(3.0D),
	GIGANTISCH(4.0D);

	@Getter
	private final double fieldSize;
}
