package de.ollie.carp.bm.lib.server.rest.v1.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@EqualsAndHashCode
@Generated
@Getter
@NoArgsConstructor
@ToString
public class SeiteDTO<T> {

	private long anzahlDatensaetzeGesamt;
	private int anzahlDatensaetzeProSeite;
	private List<T> content;
	private int seitennummer;
}
