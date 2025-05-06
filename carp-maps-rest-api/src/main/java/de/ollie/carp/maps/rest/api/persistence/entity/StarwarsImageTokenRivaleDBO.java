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
@Entity(name = "StarwarsImageTokenRivale")
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "STARWARS_IMAGE_TOKEN_RIVALE")
@ToString(callSuper = true)
public class StarwarsImageTokenRivaleDBO extends StarwarsImageTokenDBO {

	@Column(name = "CURRENT_KRITISCHE_TREFFER", nullable = false)
	private int currentKritischeTreffer;

	@Column(name = "KRITISCHE_TREFFER", nullable = false)
	private int kritischeTreffer;
}
