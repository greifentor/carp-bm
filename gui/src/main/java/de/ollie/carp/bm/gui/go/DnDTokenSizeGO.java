package de.ollie.carp.bm.gui.go;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum DnDTokenSizeGO {
	WINZIG(0.5D),
	KLEIN(1.0D),
	MITTEL(1.0D),
	GROSS(2.0D),
	RIESIG(3.0D),
	GIGANTISCH(4.0D);

	@Getter
	private final double fieldSize;
}
