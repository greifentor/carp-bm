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
@Entity(name = "StarwarsImageTokenSpielercharakter")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "STARWARS_IMAGE_TOKEN_SPIELERCHARAKTER")
@ToString(callSuper = true)
public class StarwarsImageTokenSpielercharakterDBO extends StarwarsImageTokenDBO {

	@Column(name = "CURRENT_ERSCHOEPFUNG", nullable = false)
	private int currentErschoepfung;

	@Column(name = "CURRENT_KRITISCHE_TREFFER", nullable = false)
	private int currentKritischeTreffer;

	@Column(name = "ERSCHOEPFUNG_LIMIT", nullable = false)
	private int erschoepfungLimit;

	@Column(name = "KONFLIKT", nullable = false)
	private int konflikt;

	@Column(name = "KRITISCHE_TREFFER", nullable = false)
	private int kritischeTreffer;
}
