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
@Entity(name = "StarwarsImageTokenHandlanger")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "STARWARS_IMAGE_TOKEN_HANDLANGER")
@ToString(callSuper = true)
public class StarwarsImageTokenHandlangerDBO extends StarwarsImageTokenDBO {

	@Column(name = "ANZAHL_MAX", nullable = false)
	private int anzahlMax;

	@Column(name = "CURRENT_ANZAHL", nullable = false)
	private int currentAnzahl;
}
