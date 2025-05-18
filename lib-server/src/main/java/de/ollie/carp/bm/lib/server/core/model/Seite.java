package de.ollie.carp.bm.lib.server.core.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Generated
@Getter
@ToString
public class Seite<T> {

	private long anzahlDatensaetzeGesamt;
	private int anzahlDatensaetzeProSeite;
	private List<T> content;
	private int seitennummer;
}
