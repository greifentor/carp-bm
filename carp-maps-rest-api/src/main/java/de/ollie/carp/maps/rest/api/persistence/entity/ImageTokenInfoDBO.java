package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "ImageTokenInfo")
@Table(name = "IMAGE_TOKEN_INFO")
public class ImageTokenInfoDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "ANGRIFFSART")
	private AngriffsartTypDBO angriffsart;

	@Column(name = "ANGRIFFSBONUS")
	private Integer angriffsbonus;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "SORT_PRIORITY", nullable = false)
	private int sortPriority;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYP", nullable = false)
	private ImageTokenInfoTypDBO typ;
}
