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
@Entity(name = "Sitzung")
@Table(name = "SITZUNG")
public class SitzungDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@Column(name = "GLOBAL_ID", nullable = false)
	private String globalId;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Enumerated(EnumType.STRING)
	@Column(name = "TYP", nullable = false)
	private SitzungTypDBO typ;

	@Column(name = "URL_SUFFIX")
	private String urlSuffix;
}
