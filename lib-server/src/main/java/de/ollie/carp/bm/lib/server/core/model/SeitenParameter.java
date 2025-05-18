package de.ollie.carp.bm.lib.server.core.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.Getter;

@EqualsAndHashCode
@Generated
@Getter
public class SeitenParameter {

	public enum Richtung {
		ABSTEIGEND,
		AUFSTEIGEND,
	}

	@AllArgsConstructor
	@EqualsAndHashCode
	@Generated
	@Getter
	public static class Sortierung {

		public Sortierung(String propertyName) {
			this(propertyName, Richtung.AUFSTEIGEND);
		}

		private String propertyName;
		private Richtung richtung;
	}

	public SeitenParameter(int seitengroesse, int seitennummer, Sortierung... sortierung) {
		this.seitengroesse = seitengroesse;
		this.seitennummer = seitennummer;
		this.sortierung = sortierung;
	}

	private int seitengroesse;
	private int seitennummer;
	private Sortierung[] sortierung;
}
