package de.ollie.carp.maps.rest.api.core.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
@ToString
public class Seite<T> {

	private long anzahlDatensaetzeGesamt;
	private int anzahlDatensaetzeProSeite;
	private List<T> content;
	private int seitennummer;
}
