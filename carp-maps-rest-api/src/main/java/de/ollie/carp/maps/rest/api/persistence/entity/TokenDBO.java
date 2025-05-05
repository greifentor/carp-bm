package de.ollie.carp.maps.rest.api.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.ToString;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "Token")
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "IMAGE")
@ToString(exclude = "image")
public class TokenDBO {

	@Id
	@Column(name = "GLOBAL_ID", nullable = false)
	private UUID id;

	@Column(name = "IMAGE", columnDefinition = "BLOB")
	private byte[] image;

	@Column(name = "IMAGE_TYPE")
	@Enumerated(EnumType.STRING)
	private ImageTypeDBO imageType;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
}
