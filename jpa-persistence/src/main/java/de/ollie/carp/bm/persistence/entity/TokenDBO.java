package de.ollie.carp.bm.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "TOKEN")
@ToString(exclude = "image")
public class TokenDBO {

	@Id
	@Column(name = "ID", nullable = false)
	private UUID id;

	@Column(name = "IMAGE", columnDefinition = "BLOB")
	private byte[] image;

	@Column(name = "NAME", nullable = false, unique = true)
	private String name;

	@Column(name = "SHAPE_TYPE", nullable = false)
	private ShapeTypeDBO shapeType;
}
