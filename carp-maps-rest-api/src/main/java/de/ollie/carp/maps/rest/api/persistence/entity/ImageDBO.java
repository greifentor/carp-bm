package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "Image")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "IMAGE")
public class ImageDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "GLOBAL_ID", nullable = false)
	private String globalId;

	@Column(name = "HEIGHT", nullable = false)
	private int height;

	@Column(name = "IMAGE", nullable = false)
	@ToString.Exclude
	private byte[] image;

	@Enumerated(EnumType.STRING)
	@Column(name = "IMAGE_FORMAT")
	private ImageFormatDBO imageFormat;

	@Enumerated(EnumType.STRING)
	@Column(name = "IMAGE_TYPE", nullable = false)
	private ImageTypeDBO imageType;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "OPEN", nullable = false)
	private boolean open;

	@Enumerated(EnumType.STRING)
	@Column(name = "SITZUNG_TYP")
	private SitzungTypDBO sitzungTyp;

	@Column(name = "WIDTH", nullable = false)
	private int width;
}
