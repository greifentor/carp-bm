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
@Entity(name = "ImageToken")
@Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "ID")
@Table(name = "IMAGE_TOKEN")
@ToString(callSuper = true)
public class ImageTokenDBO extends ImageDBO {

	@Enumerated(EnumType.STRING)
	@Column(name = "PLACE_MODE", nullable = false)
	private PlaceModeDBO placeMode;

	@Enumerated(EnumType.STRING)
	@Column(name = "TOKEN_TYP", nullable = false)
	private TokenTypDBO tokenTyp;
}
