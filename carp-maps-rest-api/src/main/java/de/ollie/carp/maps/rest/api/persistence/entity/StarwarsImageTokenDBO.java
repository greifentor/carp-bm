package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
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
@Entity(name = "StarwarsImageToken")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "STARWARS_IMAGE_TOKEN")
@ToString(callSuper = true)
public class StarwarsImageTokenDBO extends ImageTokenDBO {

	@Enumerated(EnumType.STRING)
	@Column(name = "CHARACTER_CATEGORY", nullable = false)
	private CharacterCategoryDBO characterCategory;

	@Column(name = "CURRENT_WUNDEN", nullable = false)
	private int currentWunden;

	@Column(name = "SPIELWERTE")
	private String spielwerte;

	@Column(name = "WUNDEN_LIMIT", nullable = false)
	private int wundenLimit;
}
