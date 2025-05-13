package de.ollie.carp.maps.rest.api.persistence.mapper;

import de.ollie.carp.maps.rest.api.core.model.DnDTokenSize;
import jakarta.inject.Named;
import java.util.Arrays;

@Named
class SubHeaderParser {

	public DnDTokenSize getTokenSize(String subHeader) {
		if ((subHeader == null) || subHeader.isBlank()) {
			return DnDTokenSize.MITTEL;
		}
		String subHeaderUC = subHeader.toUpperCase();
		return Arrays
			.asList(DnDTokenSize.values())
			.stream()
			.filter(dts -> subHeaderUC.contains(dts.name()))
			.findAny()
			.orElse(DnDTokenSize.MITTEL);
	}
}
