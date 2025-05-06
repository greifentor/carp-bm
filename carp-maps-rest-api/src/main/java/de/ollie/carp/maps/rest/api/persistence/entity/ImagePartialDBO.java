package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "ImagePartial")
@Table(name = "IMAGE")
public class ImagePartialDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private long id;

	@Enumerated(EnumType.STRING)
	@Column(name = "IMAGE_TYPE", nullable = false)
	private ImageTypeDBO imageType;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "SITZUNG_TYP")
	private SitzungTypDBO sitzungTyp;
}
