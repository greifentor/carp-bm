package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "SitzungImage")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "SITZUNG_IMAGE")
public class SitzungImageDBO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false)
	private long id;

	@JoinColumn(name = "IMAGE", nullable = false, referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private ImageDBO image;

	@JoinColumn(name = "SITZUNG", nullable = false, referencedColumnName = "ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private SitzungDBO sitzung;

	@Column(name = "GLOBAL_ID", nullable = false)
	private String globalId;

	@Column(name = "LABEL", nullable = false)
	private String label;

	@Column(name = "TEMPORARY_NAME")
	private String temporaryName;

	@Column(name = "X", nullable = false)
	private int x;

	@Column(name = "Y", nullable = false)
	private int y;
}
