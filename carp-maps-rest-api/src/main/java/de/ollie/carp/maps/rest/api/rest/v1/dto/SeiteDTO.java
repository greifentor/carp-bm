package de.ollie.carp.maps.rest.api.rest.v1.dto;

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
public class SeiteDTO<T> {

	private long anzahlDatensaetzeGesamt;
	private int anzahlDatensaetzeProSeite;
	private List<T> content;
	private int seitennummer;
}
