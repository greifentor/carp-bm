package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "StarwarsSitzungImageToken")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "STARWARS_SITZUNG_IMAGE_TOKEN")
@ToString(callSuper = true)
public class StarwarsSitzungImageTokenDBO extends SitzungImageDBO {

	@Column(name = "CURRENT_ANZAHL")
	private Integer currentAnzahl;

	@Column(name = "CURRENT_ERSCHOEPFUNG", nullable = false)
	private int currentErschoepfung;

	@Column(name = "CURRENT_KONFLIKT", nullable = false)
	private int currentKonflikt;

	@Column(name = "CURRENT_KRITISCHE_TREFFER", nullable = false)
	private int currentKritischeTreffer;

	@Column(name = "CURRENT_WUNDEN", nullable = false)
	private int currentWunden;

	@Column(name = "INITIATIVE_ERFOLGE", nullable = false)
	private int initiativeErfolge;

	@Column(name = "INITIATIVE_VORTEILE", nullable = false)
	private int initiativeVorteile;
}
