package de.ollie.carp.bm.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.Data;
import lombok.Generated;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
@Generated
@Entity(name = "Image")
@Table(name = "IMAGE")
public class ImageDBO {

	@Id
	@Column(name = "GLOBAL_ID", nullable = false)
	private UUID id;
}
